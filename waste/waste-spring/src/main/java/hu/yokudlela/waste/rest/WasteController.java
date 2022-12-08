/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.waste.rest;

import hu.yokudlela.waste.Waste;
import hu.yokudlela.waste.store.service.WasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jakab
 */
@RestController()
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8081"})
@Validated
public class WasteController {

    @Autowired
    private WasteService service;


    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sikeres hozzáadás")})
    @Operation(summary = "Bevételezés",
            security = {
                @SecurityRequirement(name = "apikey", scopes = {"waste"}),
                @SecurityRequirement(name = "openid", scopes = {"waste"}),
                @SecurityRequirement(name = "oauth2", scopes = {"waste"}),})
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("waste")
    public void addWaste(@Parameter(description = "Szemét hozzáadás", required = true) @RequestBody Waste waste) {
        service.wasteIncome(waste);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sikeres törlés", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "401", description = "Nincs bejelentkezve", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "403", description = "Nincs jogosultság", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Nincs ilyen id-jű termék", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "405", description = "Method not allowed", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Kivételezés", security = {
        @SecurityRequirement(name = "apikey", scopes = {"storage"}),
        @SecurityRequirement(name = "openid", scopes = {"storage"}),
        @SecurityRequirement(name = "oauth2", scopes = {"storage"}),})
    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("waste")
    public void emptyTrash() {
        service.emptyTrash();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Waste.class)))}),})
    @Operation(summary = "Selejtezés")
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Waste> getCurrentTrash() {
      return  service.getCurrentTrash();
    }
}

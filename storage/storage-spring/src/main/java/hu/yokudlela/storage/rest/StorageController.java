/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage.rest;

import hu.yokudlela.storage.Product;
import hu.yokudlela.storage.ProductQuantity;
import hu.yokudlela.storage.store.ProductRepository;
import hu.yokudlela.storage.store.service.StorageService;
import hu.yokudlela.storage.store.service.WasteControllerService;
import hu.yokudlela.waste.java.clients.api.WasteControllerApi;
import hu.yokudlela.waste.java.clients.invoker.ApiException;
import hu.yokudlela.waste.java.clients.model.Waste;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @author jakab
 */
@RestController()
@CrossOrigin(origins = "http://localhost:8080")
@Validated
public class StorageController {

    @Autowired
    private StorageService service;
    
    @Autowired
    private WasteControllerService wasteControllerService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductQuantity.class)))}),})
    @Operation(summary = "Aktuális készlet lekérdezése termékenként")
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductQuantity> getProductQuantities() {
        return service.getProductQuantities();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sikeres hozzáadás")})
    @Operation(summary = "Bevételezés",
            security = {
                @SecurityRequirement(name = "apikey", scopes = {"storage"}),
                @SecurityRequirement(name = "openid", scopes = {"storage"}),
                @SecurityRequirement(name = "oauth2", scopes = {"storage"}),})
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("storage")
    public void addProduct(@Parameter(description = "Termék hozzáadás", required = true) @RequestBody Product product) {
        service.addProduct(product);
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
    @PutMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("storage")
    public void deleteProduct(HttpServletRequest http, @Parameter(description = "Termék törlése", required = true) @RequestParam(name = "productid", required = true) Long id) {
        Optional<Product> prod = service.getProductById(id);
        if (prod.isPresent())
        {
            Product p = prod.get();
           WasteControllerApi api =  this.wasteControllerService.getClientInstanceWithToken(http.getHeader("Authorization"));
            try {
                api.addWaste(new Waste().count(p.getCount()).arrivedAt(LocalDateTime.now()).name(p.getName()).price(p.getPrice()));
            } catch (ApiException ex) {
                Logger.getLogger(StorageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            service.deleteProduct(id);
        }
        
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")})
    @Operation(summary = "Selejtezés", security = {
        @SecurityRequirement(name = "apikey", scopes = {"storage"}),
        @SecurityRequirement(name = "openid", scopes = {"storage"}),
        @SecurityRequirement(name = "oauth2", scopes = {"storage"}),})
    @DeleteMapping(path = "/removeexpired", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeExpiredProducts(@Parameter(description = "Lejárati dátum", required = true) @RequestParam(name = "expirationdate", required = true) String date) {
        service.removeExpiredProducts(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(date, LocalDateTime::from));
    }

}

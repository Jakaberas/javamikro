/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage.rest;

import hu.yokudlela.storage.Product;
import hu.yokudlela.storage.ProductQuantity;
import hu.yokudlela.storage.store.ProductRepository;
import hu.yokudlela.storage.store.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jakab
 */
@RestController()
//@RequestMapping(path = "/storage")
@Validated
public class StorageController {
    @Autowired
    private ProductRepository productRepo;
    
    @Autowired
    private StorageService service;

    public StorageController() {
        System.out.println("Betöltött ez a fos!!!");
    }
    
    @RequestMapping("/hello")
    public String test()
    {
        return "Hello";
    }
    
    
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés", 
	    content = { @Content(mediaType = "application/json",  
	    array = @ArraySchema(schema = @Schema(implementation = ProductQuantity.class))) }),})
    @Operation(summary = "Aktuális készlet lekérdezése termékenként")    
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)    
    public List<ProductQuantity> getProductQuantities()
    {
        return service.getProductQuantities();
    }
    
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")})
    @Operation(summary = "Bevételezés")    
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)    
    public void addProduct(@Parameter(description = "Termék hozzáadás", required = true) @RequestParam(name = "product", required = true) Product product)
    {
        service.addProduct(product);
    }
    
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")})
    @Operation(summary = "Kivételezés")    
    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)    
    public void deleteProduct(@Parameter(description = "Termék törlése", required = true) @RequestParam(name = "productid", required = true) int id)
    {
        service.deleteProduct(id);
    }
    
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")})
    @Operation(summary = "Selejtezés")    
    @DeleteMapping(path = "/removeexpired", produces = MediaType.APPLICATION_JSON_VALUE)    
    public void removeExpiredProducts(@Parameter(description = "Lejárati dátum", required = true) @RequestParam(name = "expirationdate", required = true) String date)
    {
        service.removeExpiredProducts(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(date, LocalDateTime::from));
    }
    
    
}

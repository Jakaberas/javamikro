/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 *
 * @author jakab
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class Product {
    String name;
    String description;
    LocalDateTime expirationDate;
    double price;
    int count;
    List<String> images = new ArrayList<>(); 

    @Builder
    public Product(String name, String description, LocalDateTime expirationDate, double price, int count) {
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
        this.price = price;
        this.count = count;
    }
    
    
    
}

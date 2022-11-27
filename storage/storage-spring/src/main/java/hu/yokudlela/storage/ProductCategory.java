/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage;
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
public class ProductCategory {
    String name;
    String description;
    List<Product> products = new ArrayList<>();

    @Builder
    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    
}

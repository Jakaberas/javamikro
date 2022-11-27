/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author jakab
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class ProductQuantity {
    String name;
    int quantity;

    @Builder
    public ProductQuantity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    
}

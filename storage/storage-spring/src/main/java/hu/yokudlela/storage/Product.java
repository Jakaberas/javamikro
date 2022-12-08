/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.*;

/**
 *
 * @author jakab
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Entity
@javax.persistence.Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    
    String name;
    String description;
    LocalDateTime expirationDate;
    double price;
    int count;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="product_category_id")
    private ProductCategory category;
    
    @Builder
    public Product(String name, String description, LocalDateTime expirationDate, double price, int count) {
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
        this.price = price;
        this.count = count;
    }
    
    
    
}

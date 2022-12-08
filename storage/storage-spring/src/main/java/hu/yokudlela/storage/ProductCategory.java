/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;

/**
 *
 * @author jakab
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Entity
@javax.persistence.Table(name="productCategory")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    String name;
    String description;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="category")
    List<Product> products = new ArrayList<>();

    @Builder
    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    
}

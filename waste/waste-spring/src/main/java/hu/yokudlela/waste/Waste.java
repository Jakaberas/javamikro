/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.waste;
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
@javax.persistence.Table(name="waste")
public class Waste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
        
    boolean removed;
    String name;
    LocalDateTime arrivedAt;
    double price;
    int count;    
    
    
    @Builder
    public Waste(String name,LocalDateTime arrivedAt, double price, int count) {
        this.name = name;
        this.arrivedAt = arrivedAt;
        this.price = price;
        this.count = count;
        this.removed = false;
    }
    
    
    
}

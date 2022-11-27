/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage.store.service;

import hu.yokudlela.storage.Product;
import hu.yokudlela.storage.ProductQuantity;
import hu.yokudlela.storage.store.ProductRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class StorageService {
    @Autowired
    ProductRepository repository;
    
    
    public List<ProductQuantity> getProductQuantities() {
        List<ProductQuantity> list = new ArrayList<ProductQuantity>();
        list.add(new ProductQuantity("uborka", 10));
        return list;
    }

    public void addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteProduct(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void removeExpiredProducts(LocalDateTime parse) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private List<Integer> getExpiredProducts(LocalDateTime date)
    {
        return null;
    }
}

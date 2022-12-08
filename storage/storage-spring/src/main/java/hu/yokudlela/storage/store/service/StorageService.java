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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
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
        Map<String, List<Product>> result = StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.groupingBy(Product::getName));
         List<ProductQuantity> list = new ArrayList<ProductQuantity>();
         result.entrySet().forEach(group -> {
             list.add(new ProductQuantity(group.getKey(),(int)group.getValue().stream().mapToInt(m->m.getCount()).count()));
        });       
        return list;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public void removeExpiredProducts(LocalDateTime parse) {   
            repository.deleteAll(this.getExpiredProducts(parse));
    }
    public Optional<Product> getProductById(Long id)
    {
        return repository.findById(id);
    }
    
    public List<Product> getExpiredProducts(LocalDateTime date)
    {
      return repository.findByExpirationDateLessThanEqual(date);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage.store;

import hu.yokudlela.storage.Product;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/**
 *
 * @author jakab
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findByName(String name);

    @Override
    public void delete(Product entity);

    @Override
    public <S extends Product> S save(S entity);
    
    public List<Product> findByExpirationDateLessThanEqual(LocalDateTime date);
    
    
    
}

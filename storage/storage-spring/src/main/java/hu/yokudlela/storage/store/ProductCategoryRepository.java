/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage.store;

import hu.yokudlela.storage.ProductCategory;
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
public interface ProductCategoryRepository extends CrudRepository<ProductCategory,Long> {

    @Override
    public void delete(ProductCategory entity);
    
    @Override
    public <S extends ProductCategory> S save(S entity);
    
}

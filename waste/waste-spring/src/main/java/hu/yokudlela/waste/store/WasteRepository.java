/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.waste.store;

import hu.yokudlela.waste.Waste;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jakab
 */
@Repository
public interface WasteRepository extends CrudRepository<Waste, Long> {

    @Override
    public void delete(Waste entity);

    @Override
    public <S extends Waste> S save(S entity);
    
    public List<Waste> findByRemovedFalse();
    
    
    
}

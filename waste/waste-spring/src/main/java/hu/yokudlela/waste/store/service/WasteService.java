/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.waste.store.service;

import hu.yokudlela.waste.Waste;
import hu.yokudlela.waste.store.WasteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakab
 */
@Service
public class WasteService {
     @Autowired
    private WasteRepository repository;
    public void wasteIncome(Waste w)
    {
        repository.save(w);
    }
    public void emptyTrash()
    {
        repository.findByRemovedFalse().forEach(v-> {
            v.setRemoved(true);
            repository.save(v);
        });
    }
    public List<Waste> getCurrentTrash()
    {
        return repository.findByRemovedFalse();
    }
}

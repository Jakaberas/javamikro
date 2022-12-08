/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.storage.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 *
 * @author Jakab
 */
@Data
@Slf4j
public class RequestBean {

    private String requestId ;
    
    private String user;
    
    private String token;
    
    private String client;
    
    private StopWatch watcher;
    
    public RequestBean(){
        this.watcher= new StopWatch();
        this.watcher.start();
    }
    
}

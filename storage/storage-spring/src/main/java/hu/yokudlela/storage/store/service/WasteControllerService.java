/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.storage.store.service;

import hu.yokudlela.storage.utils.RequestBean;
import hu.yokudlela.waste.java.clients.api.WasteControllerApi;
import hu.yokudlela.waste.java.clients.invoker.auth.OAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakab
 */
@Service
public class WasteControllerService {
    @Value("${client.waste}")
    private String path;
    
    @Autowired
    private RequestBean rqb;
    public WasteControllerApi getClientInstance()
    {
        WasteControllerApi api = new WasteControllerApi();
        api.getApiClient().setBasePath(path);
        return api;
    }
    public WasteControllerApi getClientInstanceWithToken(String token)
    {
        WasteControllerApi api = new WasteControllerApi(new CustomApiClient());
        ((OAuth)(api.getApiClient().setBasePath(path).getAuthentication("oauth2"))).setAccessToken(token.substring("Bearer ".length()));
        return api;
    }
}

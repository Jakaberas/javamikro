/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.storage.store.service;

import hu.yokudlela.waste.java.clients.invoker.Pair;
import hu.yokudlela.waste.java.clients.invoker.auth.Authentication;
import hu.yokudlela.waste.java.clients.invoker.ApiClient;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jakab
 */
public class CustomApiClient extends ApiClient {

    public CustomApiClient() {
        super();
    }

    protected void updateParamsForAuth(String[] authNames, List<Pair> queryParams, Map<String, String> headerParams) {
        for (String authName : authNames) {
            Authentication auth = authentications.get(authName);
            if (auth != null) {
                auth.applyToParams(queryParams, headerParams);
            }
        }
    }
}

package hu.yokudlela.waste.java.clients.api;

import hu.yokudlela.waste.java.clients.invoker.ApiException;
import hu.yokudlela.waste.java.clients.invoker.ApiClient;
import hu.yokudlela.waste.java.clients.invoker.Configuration;
import hu.yokudlela.waste.java.clients.invoker.Pair;

import javax.ws.rs.core.GenericType;

import hu.yokudlela.waste.java.clients.model.Waste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-07T18:55:23.587264400+01:00[Europe/Prague]")public class WasteControllerApi {
  private ApiClient apiClient;

  public WasteControllerApi() {
    this(Configuration.getDefaultApiClient());
  }

  public WasteControllerApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Bev�telez�s
   * 
   * @param body  (required)
   * @throws ApiException if fails to make API call
   */
  public void addWaste(Waste body) throws ApiException {
    Object localVarPostBody = body;
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling addWaste");
    }
    // create path and map variables
    String localVarPath = "/add";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "apikey", "oauth2", "openid" };

    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Kiv�telez�s
   * 
   * @throws ApiException if fails to make API call
   */
  public void emptyTrash() throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/delete";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "apikey", "oauth2", "openid" };

    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Selejtez�s
   * 
   * @return List&lt;Waste&gt;
   * @throws ApiException if fails to make API call
   */
  public List<Waste> getCurrentTrash() throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/get";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<Waste>> localVarReturnType = new GenericType<List<Waste>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}

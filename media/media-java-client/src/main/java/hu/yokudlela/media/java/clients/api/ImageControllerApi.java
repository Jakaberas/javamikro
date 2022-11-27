package hu.yokudlela.media.java.clients.api;

import hu.yokudlela.media.java.clients.invoker.ApiException;
import hu.yokudlela.media.java.clients.invoker.ApiClient;
import hu.yokudlela.media.java.clients.invoker.Configuration;
import hu.yokudlela.media.java.clients.invoker.Pair;

import javax.ws.rs.core.GenericType;

import java.io.File;
import hu.yokudlela.media.java.clients.model.ImageProcessModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-11-27T13:23:14.288640500+01:00[Europe/Prague]")public class ImageControllerApi {
  private ApiClient apiClient;

  public ImageControllerApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ImageControllerApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * K�pfelt�lt�s
   * 
   * @param file  (optional)
   * @param requestId H�v� k�r�s azonos�t�ja (optional)
   * @return List&lt;ImageProcessModel&gt;
   * @throws ApiException if fails to make API call
   */
  public List<ImageProcessModel> addFiles(File file, String requestId) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/image/addFiles";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (requestId != null)
      localVarHeaderParams.put("requestId", apiClient.parameterToString(requestId));
    if (file != null)
      localVarFormParams.put("file", file);

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "apikey", "oauth2", "openid" };

    GenericType<List<ImageProcessModel>> localVarReturnType = new GenericType<List<ImageProcessModel>>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * K�pfelt�lt�s
   * 
   * @param id  (required)
   * @param requestId H�v� k�r�s azonos�t�ja (optional)
   * @return ImageProcessModel
   * @throws ApiException if fails to make API call
   */
  public ImageProcessModel deleteFiles(String id, String requestId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling deleteFiles");
    }
    // create path and map variables
    String localVarPath = "/image/deleteFile/{id}"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (requestId != null)
      localVarHeaderParams.put("requestId", apiClient.parameterToString(requestId));

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "apikey", "oauth2", "openid" };

    GenericType<ImageProcessModel> localVarReturnType = new GenericType<ImageProcessModel>() {};
    return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * K�plet�lt�s
   * 
   * @param id  (required)
   * @param requestId H�v� k�r�s azonos�t�ja (optional)
   * @return List&lt;byte[]&gt;
   * @throws ApiException if fails to make API call
   */
  public List<byte[]> getImage(String id, String requestId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling getImage");
    }
    // create path and map variables
    String localVarPath = "/image/getImage/{id}"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (requestId != null)
      localVarHeaderParams.put("requestId", apiClient.parameterToString(requestId));

    final String[] localVarAccepts = {
      "image/jpeg"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<byte[]>> localVarReturnType = new GenericType<List<byte[]>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * K�pinform�ci�k
   * 
   * @param id  (required)
   * @param requestId H�v� k�r�s azonos�t�ja (optional)
   * @return ImageProcessModel
   * @throws ApiException if fails to make API call
   */
  public ImageProcessModel getImageInfo(String id, String requestId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling getImageInfo");
    }
    // create path and map variables
    String localVarPath = "/image/getimageinfo/{id}"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (requestId != null)
      localVarHeaderParams.put("requestId", apiClient.parameterToString(requestId));

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ImageProcessModel> localVarReturnType = new GenericType<ImageProcessModel>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}

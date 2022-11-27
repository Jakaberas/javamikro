# ImageControllerApi

All URIs are relative to *http://yokudlela:8060/media*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addFiles**](ImageControllerApi.md#addFiles) | **POST** /image/addFiles | K�pfelt�lt�s
[**deleteFiles**](ImageControllerApi.md#deleteFiles) | **DELETE** /image/deleteFile/{id} | K�pfelt�lt�s
[**getImage**](ImageControllerApi.md#getImage) | **GET** /image/getImage/{id} | K�plet�lt�s
[**getImageInfo**](ImageControllerApi.md#getImageInfo) | **GET** /image/getimageinfo/{id} | K�pinform�ci�k

<a name="addFiles"></a>
# **addFiles**
> List&lt;ImageProcessModel&gt; addFiles(file, requestId)

K�pfelt�lt�s

### Example
```java
// Import classes:
//import hu.yokudlela.media.java.clients.invoker.ApiClient;
//import hu.yokudlela.media.java.clients.invoker.ApiException;
//import hu.yokudlela.media.java.clients.invoker.Configuration;
//import hu.yokudlela.media.java.clients.invoker.auth.*;
//import hu.yokudlela.media.java.clients.api.ImageControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: apikey
ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
apikey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//apikey.setApiKeyPrefix("Token");

// Configure OAuth2 access token for authorization: oauth2
OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
oauth2.setAccessToken("YOUR ACCESS TOKEN");


ImageControllerApi apiInstance = new ImageControllerApi();
File file = new File("file_example"); // File | 
String requestId = ""; // String | H�v� k�r�s azonos�t�ja
try {
    List<ImageProcessModel> result = apiInstance.addFiles(file, requestId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImageControllerApi#addFiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**|  | [optional]
 **requestId** | **String**| H�v� k�r�s azonos�t�ja | [optional]

### Return type

[**List&lt;ImageProcessModel&gt;**](ImageProcessModel.md)

### Authorization

[apikey](../README.md#apikey)[oauth2](../README.md#oauth2)[openid](../README.md#openid)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="deleteFiles"></a>
# **deleteFiles**
> ImageProcessModel deleteFiles(id, requestId)

K�pfelt�lt�s

### Example
```java
// Import classes:
//import hu.yokudlela.media.java.clients.invoker.ApiClient;
//import hu.yokudlela.media.java.clients.invoker.ApiException;
//import hu.yokudlela.media.java.clients.invoker.Configuration;
//import hu.yokudlela.media.java.clients.invoker.auth.*;
//import hu.yokudlela.media.java.clients.api.ImageControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: apikey
ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
apikey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//apikey.setApiKeyPrefix("Token");

// Configure OAuth2 access token for authorization: oauth2
OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
oauth2.setAccessToken("YOUR ACCESS TOKEN");


ImageControllerApi apiInstance = new ImageControllerApi();
String id = "id_example"; // String | 
String requestId = ""; // String | H�v� k�r�s azonos�t�ja
try {
    ImageProcessModel result = apiInstance.deleteFiles(id, requestId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImageControllerApi#deleteFiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **requestId** | **String**| H�v� k�r�s azonos�t�ja | [optional]

### Return type

[**ImageProcessModel**](ImageProcessModel.md)

### Authorization

[apikey](../README.md#apikey)[oauth2](../README.md#oauth2)[openid](../README.md#openid)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getImage"></a>
# **getImage**
> List&lt;byte[]&gt; getImage(id, requestId)

K�plet�lt�s

### Example
```java
// Import classes:
//import hu.yokudlela.media.java.clients.invoker.ApiException;
//import hu.yokudlela.media.java.clients.api.ImageControllerApi;


ImageControllerApi apiInstance = new ImageControllerApi();
String id = "id_example"; // String | 
String requestId = ""; // String | H�v� k�r�s azonos�t�ja
try {
    List<byte[]> result = apiInstance.getImage(id, requestId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImageControllerApi#getImage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **requestId** | **String**| H�v� k�r�s azonos�t�ja | [optional]

### Return type

**List&lt;byte[]&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: image/jpeg

<a name="getImageInfo"></a>
# **getImageInfo**
> ImageProcessModel getImageInfo(id, requestId)

K�pinform�ci�k

### Example
```java
// Import classes:
//import hu.yokudlela.media.java.clients.invoker.ApiException;
//import hu.yokudlela.media.java.clients.api.ImageControllerApi;


ImageControllerApi apiInstance = new ImageControllerApi();
String id = "id_example"; // String | 
String requestId = ""; // String | H�v� k�r�s azonos�t�ja
try {
    ImageProcessModel result = apiInstance.getImageInfo(id, requestId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImageControllerApi#getImageInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **requestId** | **String**| H�v� k�r�s azonos�t�ja | [optional]

### Return type

[**ImageProcessModel**](ImageProcessModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


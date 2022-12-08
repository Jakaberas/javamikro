# WasteControllerApi

All URIs are relative to *http://yokudlela:8081/waste*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addWaste**](WasteControllerApi.md#addWaste) | **POST** /add | Bev�telez�s
[**emptyTrash**](WasteControllerApi.md#emptyTrash) | **DELETE** /delete | Kiv�telez�s
[**getCurrentTrash**](WasteControllerApi.md#getCurrentTrash) | **GET** /get | Selejtez�s

<a name="addWaste"></a>
# **addWaste**
> addWaste(body)

Bev�telez�s

### Example
```java
// Import classes:
//import hu.yokudlela.waste.java.clients.invoker.ApiClient;
//import hu.yokudlela.waste.java.clients.invoker.ApiException;
//import hu.yokudlela.waste.java.clients.invoker.Configuration;
//import hu.yokudlela.waste.java.clients.invoker.auth.*;
//import hu.yokudlela.waste.java.clients.api.WasteControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: apikey
ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
apikey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//apikey.setApiKeyPrefix("Token");

// Configure OAuth2 access token for authorization: oauth2
OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
oauth2.setAccessToken("YOUR ACCESS TOKEN");


WasteControllerApi apiInstance = new WasteControllerApi();
Waste body = new Waste(); // Waste | 
try {
    apiInstance.addWaste(body);
} catch (ApiException e) {
    System.err.println("Exception when calling WasteControllerApi#addWaste");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Waste**](Waste.md)|  |

### Return type

null (empty response body)

### Authorization

[apikey](../README.md#apikey)[oauth2](../README.md#oauth2)[openid](../README.md#openid)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="emptyTrash"></a>
# **emptyTrash**
> emptyTrash()

Kiv�telez�s

### Example
```java
// Import classes:
//import hu.yokudlela.waste.java.clients.invoker.ApiClient;
//import hu.yokudlela.waste.java.clients.invoker.ApiException;
//import hu.yokudlela.waste.java.clients.invoker.Configuration;
//import hu.yokudlela.waste.java.clients.invoker.auth.*;
//import hu.yokudlela.waste.java.clients.api.WasteControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: apikey
ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
apikey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//apikey.setApiKeyPrefix("Token");

// Configure OAuth2 access token for authorization: oauth2
OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
oauth2.setAccessToken("YOUR ACCESS TOKEN");


WasteControllerApi apiInstance = new WasteControllerApi();
try {
    apiInstance.emptyTrash();
} catch (ApiException e) {
    System.err.println("Exception when calling WasteControllerApi#emptyTrash");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[apikey](../README.md#apikey)[oauth2](../README.md#oauth2)[openid](../README.md#openid)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCurrentTrash"></a>
# **getCurrentTrash**
> List&lt;Waste&gt; getCurrentTrash()

Selejtez�s

### Example
```java
// Import classes:
//import hu.yokudlela.waste.java.clients.invoker.ApiException;
//import hu.yokudlela.waste.java.clients.api.WasteControllerApi;


WasteControllerApi apiInstance = new WasteControllerApi();
try {
    List<Waste> result = apiInstance.getCurrentTrash();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WasteControllerApi#getCurrentTrash");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Waste&gt;**](Waste.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


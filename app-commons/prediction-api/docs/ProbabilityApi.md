# ProbabilityApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**probabilityCount**](ProbabilityApi.md#probabilityCount) | **GET** /Probabilities/count | Count instances of the model matched by where from the data source.
[**probabilityCurrent**](ProbabilityApi.md#probabilityCurrent) | **GET** /Probabilities/current | 
[**probabilityFind**](ProbabilityApi.md#probabilityFind) | **GET** /Probabilities | Find all instances of the model matched by filter from the data source.
[**probabilityFindOne**](ProbabilityApi.md#probabilityFindOne) | **GET** /Probabilities/findOne | Find first instance of the model matched by filter from the data source.
[**probabilityPrediction**](ProbabilityApi.md#probabilityPrediction) | **GET** /Probabilities/prediction | 


<a name="probabilityCount"></a>
# **probabilityCount**
> Object probabilityCount(where)

Count instances of the model matched by where from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;

ProbabilityApi apiInstance = new ProbabilityApi();
String where = "where_example"; // String | Criteria to match model instances
try {
    Object result = apiInstance.probabilityCount(where);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProbabilityApi#probabilityCount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **where** | **String**| Criteria to match model instances | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="probabilityCurrent"></a>
# **probabilityCurrent**
> Object probabilityCurrent(lat, lng)



### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;

ProbabilityApi apiInstance = new ProbabilityApi();
Double lat = 3.4D; // Double | 
Double lng = 3.4D; // Double | 
try {
    Object result = apiInstance.probabilityCurrent(lat, lng);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProbabilityApi#probabilityCurrent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lat** | **Double**|  | [optional]
 **lng** | **Double**|  | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="probabilityFind"></a>
# **probabilityFind**
> List&lt;Probability&gt; probabilityFind(filter)

Find all instances of the model matched by filter from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;

ProbabilityApi apiInstance = new ProbabilityApi();
String filter = "filter_example"; // String | Filter defining fields, where, include, order, offset, and limit
try {
    List<Probability> result = apiInstance.probabilityFind(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProbabilityApi#probabilityFind");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| Filter defining fields, where, include, order, offset, and limit | [optional]

### Return type

[**List&lt;Probability&gt;**](Probability.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="probabilityFindOne"></a>
# **probabilityFindOne**
> Probability probabilityFindOne(filter)

Find first instance of the model matched by filter from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;

ProbabilityApi apiInstance = new ProbabilityApi();
String filter = "filter_example"; // String | Filter defining fields, where, include, order, offset, and limit
try {
    Probability result = apiInstance.probabilityFindOne(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProbabilityApi#probabilityFindOne");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| Filter defining fields, where, include, order, offset, and limit | [optional]

### Return type

[**Probability**](Probability.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="probabilityPrediction"></a>
# **probabilityPrediction**
> Probability probabilityPrediction(date, lat, lng)



### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;

ProbabilityApi apiInstance = new ProbabilityApi();
Date date = new Date(); // Date | 
Double lat = 3.4D; // Double | 
Double lng = 3.4D; // Double | 
try {
    Probability result = apiInstance.probabilityPrediction(date, lat, lng);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProbabilityApi#probabilityPrediction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **date** | **Date**|  | [optional]
 **lat** | **Double**|  | [optional]
 **lng** | **Double**|  | [optional]

### Return type

[**Probability**](Probability.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript


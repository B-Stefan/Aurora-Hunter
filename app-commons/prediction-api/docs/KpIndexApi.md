# KpIndexApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**kpIndexCount**](KpIndexApi.md#kpIndexCount) | **GET** /KpIndices/count | Count instances of the model matched by where from the data source.
[**kpIndexCurrent**](KpIndexApi.md#kpIndexCurrent) | **GET** /KpIndices/current | 
[**kpIndexFind**](KpIndexApi.md#kpIndexFind) | **GET** /KpIndices | Find all instances of the model matched by filter from the data source.
[**kpIndexFindOne**](KpIndexApi.md#kpIndexFindOne) | **GET** /KpIndices/findOne | Find first instance of the model matched by filter from the data source.
[**kpIndexPrediction**](KpIndexApi.md#kpIndexPrediction) | **GET** /KpIndices/prediction | Returns all instance wich a utc > current utc 
[**kpIndexPrediction3Days**](KpIndexApi.md#kpIndexPrediction3Days) | **GET** /KpIndices/prediction/daily |  Returns a conclusion for the next 3 days with min and max prediction for each day


<a name="kpIndexCount"></a>
# **kpIndexCount**
> Object kpIndexCount(where)

Count instances of the model matched by where from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

KpIndexApi apiInstance = new KpIndexApi();
String where = "where_example"; // String | Criteria to match model instances
try {
    Object result = apiInstance.kpIndexCount(where);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KpIndexApi#kpIndexCount");
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

<a name="kpIndexCurrent"></a>
# **kpIndexCurrent**
> KpIndex kpIndexCurrent()



### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

KpIndexApi apiInstance = new KpIndexApi();
try {
    KpIndex result = apiInstance.kpIndexCurrent();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KpIndexApi#kpIndexCurrent");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**KpIndex**](KpIndex.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="kpIndexFind"></a>
# **kpIndexFind**
> List&lt;KpIndex&gt; kpIndexFind(filter)

Find all instances of the model matched by filter from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

KpIndexApi apiInstance = new KpIndexApi();
String filter = "filter_example"; // String | Filter defining fields, where, include, order, offset, and limit
try {
    List<KpIndex> result = apiInstance.kpIndexFind(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KpIndexApi#kpIndexFind");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| Filter defining fields, where, include, order, offset, and limit | [optional]

### Return type

[**List&lt;KpIndex&gt;**](KpIndex.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="kpIndexFindOne"></a>
# **kpIndexFindOne**
> KpIndex kpIndexFindOne(filter)

Find first instance of the model matched by filter from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

KpIndexApi apiInstance = new KpIndexApi();
String filter = "filter_example"; // String | Filter defining fields, where, include, order, offset, and limit
try {
    KpIndex result = apiInstance.kpIndexFindOne(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KpIndexApi#kpIndexFindOne");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| Filter defining fields, where, include, order, offset, and limit | [optional]

### Return type

[**KpIndex**](KpIndex.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="kpIndexPrediction"></a>
# **kpIndexPrediction**
> List&lt;KpIndex&gt; kpIndexPrediction()



### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

KpIndexApi apiInstance = new KpIndexApi();
try {
    List<KpIndex> result = apiInstance.kpIndexPrediction();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KpIndexApi#kpIndexPrediction");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;KpIndex&gt;**](KpIndex.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="kpIndexPrediction3Days"></a>
# **kpIndexPrediction3Days**
> List&lt;KpIndexDayPrediction&gt; kpIndexPrediction3Days()



### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

KpIndexApi apiInstance = new KpIndexApi();
try {
    List<KpIndexDayPrediction> result = apiInstance.kpIndexPrediction3Days();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KpIndexApi#kpIndexPrediction3Days");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;KpIndexDayPrediction&gt;**](KpIndexDayPrediction.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript


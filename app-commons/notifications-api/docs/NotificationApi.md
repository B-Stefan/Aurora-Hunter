# NotificationApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**notificationCheckNotifications**](NotificationApi.md#notificationCheckNotifications) | **GET** /Notifications/checkNotifications | 
[**notificationCount**](NotificationApi.md#notificationCount) | **GET** /Notifications/count | Count instances of the model matched by where from the data source.
[**notificationCreateChangeStreamGetNotificationsChangeStream**](NotificationApi.md#notificationCreateChangeStreamGetNotificationsChangeStream) | **GET** /Notifications/change-stream | Create a change stream.
[**notificationCreateChangeStreamPostNotificationsChangeStream**](NotificationApi.md#notificationCreateChangeStreamPostNotificationsChangeStream) | **POST** /Notifications/change-stream | Create a change stream.
[**notificationDeleteById**](NotificationApi.md#notificationDeleteById) | **DELETE** /Notifications/{id} | Delete a model instance by id from the data source.
[**notificationFind**](NotificationApi.md#notificationFind) | **GET** /Notifications | Find all instances of the model matched by filter from the data source.
[**notificationFindOne**](NotificationApi.md#notificationFindOne) | **GET** /Notifications/findOne | Find first instance of the model matched by filter from the data source.
[**notificationPrototypeUpdateAttributes**](NotificationApi.md#notificationPrototypeUpdateAttributes) | **PUT** /Notifications/{id} | Update attributes for a model instance and persist it into the data source.
[**notificationUpsert**](NotificationApi.md#notificationUpsert) | **PUT** /Notifications | Update an existing model instance or insert a new one into the data source.


<a name="notificationCheckNotifications"></a>
# **notificationCheckNotifications**
> List&lt;Object&gt; notificationCheckNotifications()



### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
try {
    List<Object> result = apiInstance.notificationCheckNotifications();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationCheckNotifications");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationCount"></a>
# **notificationCount**
> InlineResponse200 notificationCount(where)

Count instances of the model matched by where from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String where = "where_example"; // String | Criteria to match model instances
try {
    InlineResponse200 result = apiInstance.notificationCount(where);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationCount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **where** | **String**| Criteria to match model instances | [optional]

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationCreateChangeStreamGetNotificationsChangeStream"></a>
# **notificationCreateChangeStreamGetNotificationsChangeStream**
> File notificationCreateChangeStreamGetNotificationsChangeStream(options)

Create a change stream.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String options = "options_example"; // String | 
try {
    File result = apiInstance.notificationCreateChangeStreamGetNotificationsChangeStream(options);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationCreateChangeStreamGetNotificationsChangeStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **options** | **String**|  | [optional]

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationCreateChangeStreamPostNotificationsChangeStream"></a>
# **notificationCreateChangeStreamPostNotificationsChangeStream**
> File notificationCreateChangeStreamPostNotificationsChangeStream(options)

Create a change stream.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String options = "options_example"; // String | 
try {
    File result = apiInstance.notificationCreateChangeStreamPostNotificationsChangeStream(options);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationCreateChangeStreamPostNotificationsChangeStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **options** | **String**|  | [optional]

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationDeleteById"></a>
# **notificationDeleteById**
> Object notificationDeleteById(id)

Delete a model instance by id from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String id = "id_example"; // String | Model id
try {
    Object result = apiInstance.notificationDeleteById(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationDeleteById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Model id |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationFind"></a>
# **notificationFind**
> List&lt;Notification&gt; notificationFind(filter)

Find all instances of the model matched by filter from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String filter = "filter_example"; // String | Filter defining fields, where, include, order, offset, and limit
try {
    List<Notification> result = apiInstance.notificationFind(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationFind");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| Filter defining fields, where, include, order, offset, and limit | [optional]

### Return type

[**List&lt;Notification&gt;**](Notification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationFindOne"></a>
# **notificationFindOne**
> Notification notificationFindOne(filter)

Find first instance of the model matched by filter from the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String filter = "filter_example"; // String | Filter defining fields, where, include, order, offset, and limit
try {
    Notification result = apiInstance.notificationFindOne(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationFindOne");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| Filter defining fields, where, include, order, offset, and limit | [optional]

### Return type

[**Notification**](Notification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationPrototypeUpdateAttributes"></a>
# **notificationPrototypeUpdateAttributes**
> Notification notificationPrototypeUpdateAttributes(id, data)

Update attributes for a model instance and persist it into the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
String id = "id_example"; // String | PersistedModel id
Notification data = new Notification(); // Notification | An object of model property name/value pairs
try {
    Notification result = apiInstance.notificationPrototypeUpdateAttributes(id, data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationPrototypeUpdateAttributes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| PersistedModel id |
 **data** | [**Notification**](Notification.md)| An object of model property name/value pairs | [optional]

### Return type

[**Notification**](Notification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript

<a name="notificationUpsert"></a>
# **notificationUpsert**
> Notification notificationUpsert(data)

Update an existing model instance or insert a new one into the data source.

### Example
```java
// Import classes:
//import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

NotificationApi apiInstance = new NotificationApi();
Notification data = new Notification(); // Notification | Model instance data
try {
    Notification result = apiInstance.notificationUpsert(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationApi#notificationUpsert");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**Notification**](Notification.md)| Model instance data | [optional]

### Return type

[**Notification**](Notification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/x-www-form-urlencoded, application/xml, text/xml
 - **Accept**: application/json, application/xml, text/xml, application/javascript, text/javascript


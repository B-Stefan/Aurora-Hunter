# Notifications-API

The notifications api provide methods to store your location and get android nofiticaions. You can also set a threshold for the notification. 

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-android-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-android-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-android-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;

public class NotificationApiExample {

    public static void main(String[] args) {
        NotificationApi apiInstance = new NotificationApi();
        try {
            List<Object> result = apiInstance.notificationCheckNotifications();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificationApi#notificationCheckNotifications");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost/api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*NotificationApi* | [**notificationCheckNotifications**](docs/NotificationApi.md#notificationCheckNotifications) | **GET** /Notifications/checkNotifications | 
*NotificationApi* | [**notificationCount**](docs/NotificationApi.md#notificationCount) | **GET** /Notifications/count | Count instances of the model matched by where from the data source.
*NotificationApi* | [**notificationCreateChangeStreamGetNotificationsChangeStream**](docs/NotificationApi.md#notificationCreateChangeStreamGetNotificationsChangeStream) | **GET** /Notifications/change-stream | Create a change stream.
*NotificationApi* | [**notificationCreateChangeStreamPostNotificationsChangeStream**](docs/NotificationApi.md#notificationCreateChangeStreamPostNotificationsChangeStream) | **POST** /Notifications/change-stream | Create a change stream.
*NotificationApi* | [**notificationDeleteById**](docs/NotificationApi.md#notificationDeleteById) | **DELETE** /Notifications/{id} | Delete a model instance by id from the data source.
*NotificationApi* | [**notificationFind**](docs/NotificationApi.md#notificationFind) | **GET** /Notifications | Find all instances of the model matched by filter from the data source.
*NotificationApi* | [**notificationFindOne**](docs/NotificationApi.md#notificationFindOne) | **GET** /Notifications/findOne | Find first instance of the model matched by filter from the data source.
*NotificationApi* | [**notificationPrototypeUpdateAttributes**](docs/NotificationApi.md#notificationPrototypeUpdateAttributes) | **PUT** /Notifications/{id} | Update attributes for a model instance and persist it into the data source.
*NotificationApi* | [**notificationUpsert**](docs/NotificationApi.md#notificationUpsert) | **PUT** /Notifications | Update an existing model instance or insert a new one into the data source.


## Documentation for Models

 - [InlineResponse200](docs/InlineResponse200.md)
 - [InlineResponse2001](docs/InlineResponse2001.md)
 - [Notification](docs/Notification.md)


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

* Stefan-B



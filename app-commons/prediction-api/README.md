# Aurora-Prediction-API

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

import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;

public class KpIndexApiExample {

    public static void main(String[] args) {
        KpIndexApi apiInstance = new KpIndexApi();
        String where = "where_example"; // String | Criteria to match model instances
        try {
            Object result = apiInstance.kpIndexCount(where);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling KpIndexApi#kpIndexCount");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost/api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*KpIndexApi* | [**kpIndexCount**](docs/KpIndexApi.md#kpIndexCount) | **GET** /KpIndices/count | Count instances of the model matched by where from the data source.
*KpIndexApi* | [**kpIndexCurrent**](docs/KpIndexApi.md#kpIndexCurrent) | **GET** /KpIndices/current | Return the current kPIndex based on the utc date 
*KpIndexApi* | [**kpIndexFind**](docs/KpIndexApi.md#kpIndexFind) | **GET** /KpIndices | Find all instances of the model matched by filter from the data source.
*KpIndexApi* | [**kpIndexFindOne**](docs/KpIndexApi.md#kpIndexFindOne) | **GET** /KpIndices/findOne | Find first instance of the model matched by filter from the data source.
*KpIndexApi* | [**kpIndexPrediction**](docs/KpIndexApi.md#kpIndexPrediction) | **GET** /KpIndices/prediction | Return all instances that are beyond today 
*KpIndexApi* | [**kpIndexPrediction3Days**](docs/KpIndexApi.md#kpIndexPrediction3Days) | **GET** /KpIndices/prediction/daily | Returns a conclusion with max and min for the next 3 days 
*ProbabilityApi* | [**probabilityCount**](docs/ProbabilityApi.md#probabilityCount) | **GET** /Probabilities/count | Count instances of the model matched by where from the data source.
*ProbabilityApi* | [**probabilityCurrent**](docs/ProbabilityApi.md#probabilityCurrent) | **GET** /Probabilities/current | Returns the current prediction based on the utc date 
*ProbabilityApi* | [**probabilityFind**](docs/ProbabilityApi.md#probabilityFind) | **GET** /Probabilities | Find all instances of the model matched by filter from the data source.
*ProbabilityApi* | [**probabilityFindOne**](docs/ProbabilityApi.md#probabilityFindOne) | **GET** /Probabilities/findOne | Find first instance of the model matched by filter from the data source.
*ProbabilityApi* | [**probabilityPrediction**](docs/ProbabilityApi.md#probabilityPrediction) | **GET** /Probabilities/prediction | 


## Documentation for Models

 - [KpIndex](docs/KpIndex.md)
 - [KpIndexDayPrediction](docs/KpIndexDayPrediction.md)
 - [MoonInformation](docs/MoonInformation.md)
 - [Probability](docs/Probability.md)
 - [SunInfromation](docs/SunInfromation.md)
 - [User](docs/User.md)



## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

* Stefan-B 



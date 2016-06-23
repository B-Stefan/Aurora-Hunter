package de.hs_bremen.aurora_hunter.commons.prediction.api;

import de.hs_bremen.aurora_hunter.commons.prediction.ApiInvoker;
import de.hs_bremen.aurora_hunter.commons.prediction.ApiException;
import de.hs_bremen.aurora_hunter.commons.prediction.Pair;

import de.hs_bremen.aurora_hunter.commons.prediction.models.*;

import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import de.hs_bremen.aurora_hunter.commons.prediction.models.InlineResponse200;
import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;
import de.hs_bremen.aurora_hunter.commons.prediction.models.ProbabilityConclusion;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ProbabilityApi {
  String basePath = "https://localhost/api";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
  * Count instances of the model matched by where from the data source.
  * 
   * @param where Criteria to match model instances
   * @return InlineResponse200
  */
  public InlineResponse200 probabilityCount (String where) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Probabilities/count".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();

          queryParams.addAll(ApiInvoker.parameterToPairs("", "where", where));


      String[] contentTypes = {
  "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
        }

      String[] authNames = new String[] {  };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (InlineResponse200) ApiInvoker.deserialize(localVarResponse, "", InlineResponse200.class);
        } else {
           return null;
        }
      } catch (ApiException ex) {
         throw ex;
      } catch (InterruptedException ex) {
         throw ex;
      } catch (ExecutionException ex) {
         if(ex.getCause() instanceof VolleyError) {
	    VolleyError volleyError = (VolleyError)ex.getCause();
	    if (volleyError.networkResponse != null) {
	       throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
	    }
         }
         throw ex;
      } catch (TimeoutException ex) {
         throw ex;
      }
  }

      /**
   * Count instances of the model matched by where from the data source.
   * 
   * @param where Criteria to match model instances
  */
  public void probabilityCount (String where, final Response.Listener<InlineResponse200> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Probabilities/count".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "where", where));


    String[] contentTypes = {
      "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

      String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((InlineResponse200) ApiInvoker.deserialize(localVarResponse,  "", InlineResponse200.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * 
  * 
   * @param lat 
   * @param lng 
   * @return Object
  */
  public Object probabilityCurrent (Double lat, Double lng) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Probabilities/current".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();

          queryParams.addAll(ApiInvoker.parameterToPairs("", "lat", lat));
          queryParams.addAll(ApiInvoker.parameterToPairs("", "lng", lng));


      String[] contentTypes = {
  "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
        }

      String[] authNames = new String[] {  };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
        } else {
           return null;
        }
      } catch (ApiException ex) {
         throw ex;
      } catch (InterruptedException ex) {
         throw ex;
      } catch (ExecutionException ex) {
         if(ex.getCause() instanceof VolleyError) {
	    VolleyError volleyError = (VolleyError)ex.getCause();
	    if (volleyError.networkResponse != null) {
	       throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
	    }
         }
         throw ex;
      } catch (TimeoutException ex) {
         throw ex;
      }
  }

      /**
   * 
   * 
   * @param lat    * @param lng 
  */
  public void probabilityCurrent (Double lat, Double lng, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Probabilities/current".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "lat", lat));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "lng", lng));


    String[] contentTypes = {
      "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

      String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Find all instances of the model matched by filter from the data source.
  * 
   * @param filter Filter defining fields, where, include, order, offset, and limit
   * @return List<Probability>
  */
  public List<Probability> probabilityFind (String filter) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Probabilities".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();

          queryParams.addAll(ApiInvoker.parameterToPairs("", "filter", filter));


      String[] contentTypes = {
  "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
        }

      String[] authNames = new String[] {  };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (List<Probability>) ApiInvoker.deserialize(localVarResponse, "array", Probability.class);
        } else {
           return null;
        }
      } catch (ApiException ex) {
         throw ex;
      } catch (InterruptedException ex) {
         throw ex;
      } catch (ExecutionException ex) {
         if(ex.getCause() instanceof VolleyError) {
	    VolleyError volleyError = (VolleyError)ex.getCause();
	    if (volleyError.networkResponse != null) {
	       throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
	    }
         }
         throw ex;
      } catch (TimeoutException ex) {
         throw ex;
      }
  }

      /**
   * Find all instances of the model matched by filter from the data source.
   * 
   * @param filter Filter defining fields, where, include, order, offset, and limit
  */
  public void probabilityFind (String filter, final Response.Listener<List<Probability>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Probabilities".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter", filter));


    String[] contentTypes = {
      "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

      String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((List<Probability>) ApiInvoker.deserialize(localVarResponse,  "array", Probability.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Find first instance of the model matched by filter from the data source.
  * 
   * @param filter Filter defining fields, where, include, order, offset, and limit
   * @return Probability
  */
  public Probability probabilityFindOne (String filter) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Probabilities/findOne".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();

          queryParams.addAll(ApiInvoker.parameterToPairs("", "filter", filter));


      String[] contentTypes = {
  "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
        }

      String[] authNames = new String[] {  };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (Probability) ApiInvoker.deserialize(localVarResponse, "", Probability.class);
        } else {
           return null;
        }
      } catch (ApiException ex) {
         throw ex;
      } catch (InterruptedException ex) {
         throw ex;
      } catch (ExecutionException ex) {
         if(ex.getCause() instanceof VolleyError) {
	    VolleyError volleyError = (VolleyError)ex.getCause();
	    if (volleyError.networkResponse != null) {
	       throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
	    }
         }
         throw ex;
      } catch (TimeoutException ex) {
         throw ex;
      }
  }

      /**
   * Find first instance of the model matched by filter from the data source.
   * 
   * @param filter Filter defining fields, where, include, order, offset, and limit
  */
  public void probabilityFindOne (String filter, final Response.Listener<Probability> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Probabilities/findOne".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter", filter));


    String[] contentTypes = {
      "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

      String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Probability) ApiInvoker.deserialize(localVarResponse,  "", Probability.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * 
  * 
   * @param date 
   * @param lat 
   * @param lng 
   * @return ProbabilityConclusion
  */
  public ProbabilityConclusion probabilityPrediction (Date date, Double lat, Double lng) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Probabilities/prediction".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();

          queryParams.addAll(ApiInvoker.parameterToPairs("", "date", date));
          queryParams.addAll(ApiInvoker.parameterToPairs("", "lat", lat));
          queryParams.addAll(ApiInvoker.parameterToPairs("", "lng", lng));


      String[] contentTypes = {
  "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
        }

      String[] authNames = new String[] {  };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (ProbabilityConclusion) ApiInvoker.deserialize(localVarResponse, "", ProbabilityConclusion.class);
        } else {
           return null;
        }
      } catch (ApiException ex) {
         throw ex;
      } catch (InterruptedException ex) {
         throw ex;
      } catch (ExecutionException ex) {
         if(ex.getCause() instanceof VolleyError) {
	    VolleyError volleyError = (VolleyError)ex.getCause();
	    if (volleyError.networkResponse != null) {
	       throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
	    }
         }
         throw ex;
      } catch (TimeoutException ex) {
         throw ex;
      }
  }

      /**
   * 
   * 
   * @param date    * @param lat    * @param lng 
  */
  public void probabilityPrediction (Date date, Double lat, Double lng, final Response.Listener<ProbabilityConclusion> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Probabilities/prediction".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "date", date));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "lat", lat));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "lng", lng));


    String[] contentTypes = {
      "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

      String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((ProbabilityConclusion) ApiInvoker.deserialize(localVarResponse,  "", ProbabilityConclusion.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
}

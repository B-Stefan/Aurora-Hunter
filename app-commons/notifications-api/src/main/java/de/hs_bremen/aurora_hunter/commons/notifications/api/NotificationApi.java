package de.hs_bremen.aurora_hunter.commons.notifications.api;

import de.hs_bremen.aurora_hunter.commons.notifications.ApiInvoker;
import de.hs_bremen.aurora_hunter.commons.notifications.ApiException;
import de.hs_bremen.aurora_hunter.commons.notifications.Pair;

import de.hs_bremen.aurora_hunter.commons.notifications.models.*;

import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import de.hs_bremen.aurora_hunter.commons.notifications.models.InlineResponse200;
import java.io.File;
import de.hs_bremen.aurora_hunter.commons.notifications.models.Notification;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class NotificationApi {
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
  * 
  * 
   * @return List<Object>
  */
  public List<Object> notificationCheckNotifications () throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Notifications/checkNotifications".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();



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
           return (List<Object>) ApiInvoker.deserialize(localVarResponse, "array", Object.class);
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

  */
  public void notificationCheckNotifications (final Response.Listener<List<Object>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Notifications/checkNotifications".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



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
              responseListener.onResponse((List<Object>) ApiInvoker.deserialize(localVarResponse,  "array", Object.class));
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
  * Count instances of the model matched by where from the data source.
  * 
   * @param where Criteria to match model instances
   * @return InlineResponse200
  */
  public InlineResponse200 notificationCount (String where) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Notifications/count".replaceAll("\\{format\\}","json");

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
  public void notificationCount (String where, final Response.Listener<InlineResponse200> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Notifications/count".replaceAll("\\{format\\}","json");

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
  * Create a change stream.
  * 
   * @param options 
   * @return File
  */
  public File notificationCreateChangeStreamGetNotificationsChangeStream (String options) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Notifications/change-stream".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();

          queryParams.addAll(ApiInvoker.parameterToPairs("", "options", options));


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
           return (File) ApiInvoker.deserialize(localVarResponse, "", File.class);
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
   * Create a change stream.
   * 
   * @param options 
  */
  public void notificationCreateChangeStreamGetNotificationsChangeStream (String options, final Response.Listener<File> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Notifications/change-stream".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "options", options));


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
              responseListener.onResponse((File) ApiInvoker.deserialize(localVarResponse,  "", File.class));
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
  * Create a change stream.
  * 
   * @param options 
   * @return File
  */
  public File notificationCreateChangeStreamPostNotificationsChangeStream (String options) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Notifications/change-stream".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();



      String[] contentTypes = {
  "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  
          if (options != null) {
          localVarBuilder.addTextBody("options", ApiInvoker.parameterToString(options), ApiInvoker.TEXT_PLAIN_UTF8);
          }
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
  formParams.put("options", ApiInvoker.parameterToString(options));
      }

      String[] authNames = new String[] {  };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (File) ApiInvoker.deserialize(localVarResponse, "", File.class);
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
   * Create a change stream.
   * 
   * @param options 
  */
  public void notificationCreateChangeStreamPostNotificationsChangeStream (String options, final Response.Listener<File> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Notifications/change-stream".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      "application/json","application/x-www-form-urlencoded","application/xml","text/xml"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      
      if (options != null) {
        localVarBuilder.addTextBody("options", ApiInvoker.parameterToString(options), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      formParams.put("options", ApiInvoker.parameterToString(options));
    }

      String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((File) ApiInvoker.deserialize(localVarResponse,  "", File.class));
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
  * Delete a model instance by id from the data source.
  * 
   * @param id Model id
   * @return Object
  */
  public Object notificationDeleteById (String id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  
      // verify the required parameter 'id' is set
      if (id == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'id' when calling notificationDeleteById",
      new ApiException(400, "Missing the required parameter 'id' when calling notificationDeleteById"));
      }
  

  // create path and map variables
  String path = "/Notifications/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();



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
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType, authNames);
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
   * Delete a model instance by id from the data source.
   * 
   * @param id Model id
  */
  public void notificationDeleteById (String id, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  
    // verify the required parameter 'id' is set
    if (id == null) {
       VolleyError error = new VolleyError("Missing the required parameter 'id' when calling notificationDeleteById",
         new ApiException(400, "Missing the required parameter 'id' when calling notificationDeleteById"));
    }
    

    // create path and map variables
    String path = "/Notifications/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



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
      apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType, authNames,
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
     * Find a model instance by id from the data source.
     *
     * @param id Model id
     * @param filter Filter defining fields and include
     * @return User
     */
    public User userFindById (String id, String filter) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling userFindById",
                    new ApiException(400, "Missing the required parameter 'id' when calling userFindById"));
        }


        // create path and map variables
        String path = "/Users/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
                return (User) ApiInvoker.deserialize(localVarResponse, "", User.class);
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
     * Find a model instance by id from the data source.
     *
     * @param id Model id   * @param filter Filter defining fields and include
     */
    public void notificationFindById (String id, String filter, final Response.Listener<Notification> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling notficationFindById",
                    new ApiException(400, "Missing the required parameter 'id' when calling notficationFindById"));
        }


        // create path and map variables
        String path = "/Notifications/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
                                responseListener.onResponse((Notification) ApiInvoker.deserialize(localVarResponse,  "", Notification.class));
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
   * @return List<Notification>
  */
  public List<Notification> notificationFind (String filter) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Notifications".replaceAll("\\{format\\}","json");

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
           return (List<Notification>) ApiInvoker.deserialize(localVarResponse, "array", Notification.class);
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
  public void notificationFind (String filter, final Response.Listener<List<Notification>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Notifications".replaceAll("\\{format\\}","json");

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
              responseListener.onResponse((List<Notification>) ApiInvoker.deserialize(localVarResponse,  "array", Notification.class));
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
   * @return Notification
  */
  public Notification notificationFindOne (String filter) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  

  // create path and map variables
  String path = "/Notifications/findOne".replaceAll("\\{format\\}","json");

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
           return (Notification) ApiInvoker.deserialize(localVarResponse, "", Notification.class);
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
  public void notificationFindOne (String filter, final Response.Listener<Notification> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  

    // create path and map variables
    String path = "/Notifications/findOne".replaceAll("\\{format\\}","json");

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
              responseListener.onResponse((Notification) ApiInvoker.deserialize(localVarResponse,  "", Notification.class));
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
  * Update attributes for a model instance and persist it into the data source.
  * 
   * @param id PersistedModel id
   * @param data An object of model property name/value pairs
   * @return Notification
  */
  public Notification notificationPrototypeUpdateAttributes (String id, Notification data) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = data;
  
      // verify the required parameter 'id' is set
      if (id == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'id' when calling notificationPrototypeUpdateAttributes",
      new ApiException(400, "Missing the required parameter 'id' when calling notificationPrototypeUpdateAttributes"));
      }
  

  // create path and map variables
  String path = "/Notifications/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();



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
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (Notification) ApiInvoker.deserialize(localVarResponse, "", Notification.class);
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
   * Update attributes for a model instance and persist it into the data source.
   * 
   * @param id PersistedModel id   * @param data An object of model property name/value pairs
  */
  public void notificationPrototypeUpdateAttributes (String id, Notification data, final Response.Listener<Notification> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = data;

  
    // verify the required parameter 'id' is set
    if (id == null) {
       VolleyError error = new VolleyError("Missing the required parameter 'id' when calling notificationPrototypeUpdateAttributes",
         new ApiException(400, "Missing the required parameter 'id' when calling notificationPrototypeUpdateAttributes"));
    }
    

    // create path and map variables
    String path = "/Notifications/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



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
      apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Notification) ApiInvoker.deserialize(localVarResponse,  "", Notification.class));
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
  * Update an existing model instance or insert a new one into the data source.
  * 
   * @param data Model instance data
   * @return Notification
  */
  public Notification notificationUpsert (Notification data) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = data;
  

  // create path and map variables
  String path = "/Notifications".replaceAll("\\{format\\}","json");

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();



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
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (Notification) ApiInvoker.deserialize(localVarResponse, "", Notification.class);
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
   * Update an existing model instance or insert a new one into the data source.
   * 
   * @param data Model instance data
  */
  public void notificationUpsert (Notification data, final Response.Listener<Notification> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = data;

  

    // create path and map variables
    String path = "/Notifications".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



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
      apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Notification) ApiInvoker.deserialize(localVarResponse,  "", Notification.class));
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

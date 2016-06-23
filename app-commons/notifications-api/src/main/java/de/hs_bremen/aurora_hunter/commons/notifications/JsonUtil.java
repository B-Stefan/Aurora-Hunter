package de.hs_bremen.aurora_hunter.commons.notifications;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import de.hs_bremen.aurora_hunter.commons.notifications.models.*;

public class JsonUtil {
  public static GsonBuilder gsonBuilder;

  static {
    gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  }

  public static Gson getGson() {
    return gsonBuilder.create();
  }

  public static String serialize(Object obj){
    return getGson().toJson(obj);
  }

  public static <T> T deserializeToList(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
  }

  public static <T> T deserializeToObject(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
  }

  public static Type getListTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("AccessToken".equalsIgnoreCase(className)) {
      return new TypeToken<List<AccessToken>>(){}.getType();
    }
    
    if ("GeoPoint".equalsIgnoreCase(className)) {
      return new TypeToken<List<GeoPoint>>(){}.getType();
    }
    
    if ("InlineResponse200".equalsIgnoreCase(className)) {
      return new TypeToken<List<InlineResponse200>>(){}.getType();
    }
    
    if ("InlineResponse2001".equalsIgnoreCase(className)) {
      return new TypeToken<List<InlineResponse2001>>(){}.getType();
    }
    
    if ("Notification".equalsIgnoreCase(className)) {
      return new TypeToken<List<Notification>>(){}.getType();
    }
    
    if ("User".equalsIgnoreCase(className)) {
      return new TypeToken<List<User>>(){}.getType();
    }
    
    return new TypeToken<List<Object>>(){}.getType();
  }

  public static Type getTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("AccessToken".equalsIgnoreCase(className)) {
      return new TypeToken<AccessToken>(){}.getType();
    }
    
    if ("GeoPoint".equalsIgnoreCase(className)) {
      return new TypeToken<GeoPoint>(){}.getType();
    }
    
    if ("InlineResponse200".equalsIgnoreCase(className)) {
      return new TypeToken<InlineResponse200>(){}.getType();
    }
    
    if ("InlineResponse2001".equalsIgnoreCase(className)) {
      return new TypeToken<InlineResponse2001>(){}.getType();
    }
    
    if ("Notification".equalsIgnoreCase(className)) {
      return new TypeToken<Notification>(){}.getType();
    }
    
    if ("User".equalsIgnoreCase(className)) {
      return new TypeToken<User>(){}.getType();
    }
    
    return new TypeToken<Object>(){}.getType();
  }

};

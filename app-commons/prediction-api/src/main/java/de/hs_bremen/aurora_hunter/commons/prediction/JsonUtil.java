package de.hs_bremen.aurora_hunter.commons.prediction;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hs_bremen.aurora_hunter.commons.prediction.models.*;

public class JsonUtil {
    public static GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException {
            try {
                DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTime();
                DateTime dateTime = dateFormatter.parseDateTime(jsonElement.getAsString());
                return dateTime.toDate();

            }catch (IllegalArgumentException e){
                throw new JsonParseException(e);
            }
        }
    }
    public static Gson getGson() {
        return gsonBuilder.create();
    }

    public static String serialize(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T deserializeToList(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
    }

    public static <T> T deserializeToObject(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
    }

    public static Type getListTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("AccessToken".equalsIgnoreCase(className)) {
            return new TypeToken<List<AccessToken>>() {
            }.getType();
        }

        if ("GeoPoint".equalsIgnoreCase(className)) {
            return new TypeToken<List<GeoPoint>>() {
            }.getType();
        }

        if ("InlineResponse200".equalsIgnoreCase(className)) {
            return new TypeToken<List<InlineResponse200>>() {
            }.getType();
        }

        if ("InlineResponse2001".equalsIgnoreCase(className)) {
            return new TypeToken<List<InlineResponse2001>>() {
            }.getType();
        }

        if ("KpIndex".equalsIgnoreCase(className)) {
            return new TypeToken<List<KpIndex>>() {
            }.getType();
        }

        if ("KpIndexDayPrediction".equalsIgnoreCase(className)) {
            return new TypeToken<List<KpIndexDayPrediction>>() {
            }.getType();
        }

        if ("MoonInformation".equalsIgnoreCase(className)) {
            return new TypeToken<List<MoonInformation>>() {
            }.getType();
        }

        if ("Probability".equalsIgnoreCase(className)) {
            return new TypeToken<List<Probability>>() {
            }.getType();
        }

        if ("ProbabilityConclusion".equalsIgnoreCase(className)) {
            return new TypeToken<List<ProbabilityConclusion>>() {
            }.getType();
        }

        if ("SunInfromation".equalsIgnoreCase(className)) {
            return new TypeToken<List<SunInfromation>>() {
            }.getType();
        }

        if ("User".equalsIgnoreCase(className)) {
            return new TypeToken<List<User>>() {
            }.getType();
        }

        return new TypeToken<List<Object>>() {
        }.getType();
    }

    public static Type getTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("AccessToken".equalsIgnoreCase(className)) {
            return new TypeToken<AccessToken>() {
            }.getType();
        }

        if ("GeoPoint".equalsIgnoreCase(className)) {
            return new TypeToken<GeoPoint>() {
            }.getType();
        }

        if ("InlineResponse200".equalsIgnoreCase(className)) {
            return new TypeToken<InlineResponse200>() {
            }.getType();
        }

        if ("InlineResponse2001".equalsIgnoreCase(className)) {
            return new TypeToken<InlineResponse2001>() {
            }.getType();
        }

        if ("KpIndex".equalsIgnoreCase(className)) {
            return new TypeToken<KpIndex>() {
            }.getType();
        }

        if ("KpIndexDayPrediction".equalsIgnoreCase(className)) {
            return new TypeToken<KpIndexDayPrediction>() {
            }.getType();
        }

        if ("MoonInformation".equalsIgnoreCase(className)) {
            return new TypeToken<MoonInformation>() {
            }.getType();
        }

        if ("Probability".equalsIgnoreCase(className)) {
            return new TypeToken<Probability>() {
            }.getType();
        }

        if ("ProbabilityConclusion".equalsIgnoreCase(className)) {
            return new TypeToken<ProbabilityConclusion>() {
            }.getType();
        }

        if ("SunInfromation".equalsIgnoreCase(className)) {
            return new TypeToken<SunInfromation>() {
            }.getType();
        }

        if ("User".equalsIgnoreCase(className)) {
            return new TypeToken<User>() {
            }.getType();
        }

        return new TypeToken<Object>() {
        }.getType();
    }

};

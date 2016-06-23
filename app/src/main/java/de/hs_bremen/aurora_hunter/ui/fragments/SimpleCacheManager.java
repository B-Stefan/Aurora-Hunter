package de.hs_bremen.aurora_hunter.ui.fragments;

import android.os.Bundle;

import com.google.gson.JsonParseException;

import java.util.Date;

import de.hs_bremen.aurora_hunter.commons.prediction.JsonUtil;
import de.hs_bremen.aurora_hunter.commons.prediction.models.ProbabilityConclusion;

public class SimpleCacheManager {

    public static final String LAST_UPDATE_KEY = "LAST_UPDATE_KEY ";
    public static final String JSON_DATA = "JSON_DATA ";

    private Bundle mSavedBundle = null ;

    private ProbabilityConclusion mLastData = null ;

    public class SimpleCacheData<T>{
        Date lastUpdate;
        T data;
    }
    public void setSavedBundle(Bundle savedBundle){
        this.mSavedBundle = savedBundle;
    }

    public void setData(ProbabilityConclusion probabilityConclusion){
        this.mLastData = probabilityConclusion;
    }
    public void setDataToBundle(final Bundle bundle){
        this.setDataToBundle(this.mLastData, bundle);
    };
    private void setDataToBundle(final ProbabilityConclusion probabilityConclusion, final  Bundle bundle){
        if(probabilityConclusion == null ){
            return;
        }
        final String jsonString = JsonUtil.serialize(probabilityConclusion);
        bundle.putString(JSON_DATA,jsonString);
        bundle.putSerializable(LAST_UPDATE_KEY,new Date());

    }
    public SimpleCacheData<ProbabilityConclusion> getDataFromBundle(){
        return  this.getDataFromBundle(this.mSavedBundle);
    }
    private SimpleCacheData<ProbabilityConclusion> getDataFromBundle(Bundle bundle){
        if(bundle == null ){
            if(this.mLastData != null){
                final SimpleCacheData<ProbabilityConclusion> simpleCacheData = new SimpleCacheData<>();
                simpleCacheData.data = this.mLastData;
            }
            return new SimpleCacheData<ProbabilityConclusion>();
        }
        final String json = bundle.getString(JSON_DATA);
        ProbabilityConclusion probabilityConclusion;

        final SimpleCacheData<ProbabilityConclusion> cacheData = new SimpleCacheData<>();
        //Try to get the cached data
        try {
           probabilityConclusion = JsonUtil.deserializeToObject(json, ProbabilityConclusion.class);
        }catch (JsonParseException e){
            probabilityConclusion = null;
        }
        cacheData.data = probabilityConclusion;

        //Get the last date
        try {
            cacheData.lastUpdate = (Date) bundle.getSerializable(LAST_UPDATE_KEY);
        }catch (Exception e){
            cacheData.lastUpdate = null;
        }
        return cacheData;
    }
}

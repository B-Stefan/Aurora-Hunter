package de.hs_bremen.aurora_hunter;

import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;
import de.hs_bremen.aurora_hunter.commons.notifications.api.UserApi;
import de.hs_bremen.aurora_hunter.commons.prediction.ApiInvoker;
import de.hs_bremen.aurora_hunter.commons.prediction.api.KpIndexApi;
import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;

public class ApiManager {

    private static final boolean isLocal = false;

    private static final String sNotificationAPIBaseUrl = "http://apps.conts.de:3001/api";

    private static final String sPredictionAPIBaseUrl = "http://apps.conts.de:3000/api";

    private static final String sNotificationAPIBaseUrl_local = "http://localhost:3000/api";

    private static final String sPredictionAPIBaseUrl_local = "http://localhost:3000/api";


    private static final ApiManager sInstance = new ApiManager();

    public static ApiManager getInstance(){
        return sInstance;
    }

    private ApiManager(){
        if(ApiInvoker.getInstance() == null) ApiInvoker.initializeInstance(null, null, 2, null, 60);
        if(de.hs_bremen.aurora_hunter.commons.notifications.ApiInvoker.getInstance() == null) de.hs_bremen.aurora_hunter.commons.notifications.ApiInvoker.initializeInstance(null, null, 2, null, 60);
    }
    public NotificationApi configure(final NotificationApi notificationApi){
        notificationApi.setBasePath(isLocal ? sNotificationAPIBaseUrl_local : sNotificationAPIBaseUrl );
        return notificationApi;
    }

    public KpIndexApi configure(final KpIndexApi api){
        api.setBasePath(isLocal ? sPredictionAPIBaseUrl_local : sPredictionAPIBaseUrl);
        return api;
    }
    public ProbabilityApi configure(final ProbabilityApi api){
        api.setBasePath(isLocal ? sPredictionAPIBaseUrl_local : sPredictionAPIBaseUrl);
        return api;
    }

    public UserApi configure(final UserApi userApi){
        userApi.setBasePath(isLocal ? sNotificationAPIBaseUrl_local : sNotificationAPIBaseUrl);
        return userApi;
    }

}

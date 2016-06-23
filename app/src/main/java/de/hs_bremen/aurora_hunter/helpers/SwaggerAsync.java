package de.hs_bremen.aurora_hunter.helpers;

import de.hs_bremen.aurora_hunter.commons.notifications.ApiException;

/**
 * @author FJ,SB,AC
 * @param <R> - ResultClass
 */
public abstract class SwaggerAsync<R> extends android.os.AsyncTask<Object,Object,SwaggerAsyncResponse<R>>{

    public interface SwaggerResultListener<Result> {
        public void onComplete(Result result);
        public void onException(ApiException e);
    }

    private SwaggerResultListener<R> resultListener;

    public SwaggerAsync(final SwaggerResultListener<R> listener){
        super();
        this.resultListener = listener;
    }

    public abstract R doApiCall() throws Exception;


    @Override
    protected SwaggerAsyncResponse<R> doInBackground(Object... ps) {
        final  SwaggerAsyncResponse re = new SwaggerAsyncResponse();

        try {
            re.response = this.doApiCall();
        }catch (Exception e){

            if(e instanceof ApiException){
                re.err = (ApiException) e;
            }else if(e instanceof de.hs_bremen.aurora_hunter.commons.prediction.ApiException) {
                de.hs_bremen.aurora_hunter.commons.prediction.ApiException ex = (de.hs_bremen.aurora_hunter.commons.prediction.ApiException) e ;
                re.err = new ApiException();
                re.err.setCode(ex.getCode());
                re.err.setMessage(ex.getMessage());
                re.err.setStackTrace(ex.getStackTrace());

            }
            else {
                throw new RuntimeException(e);
            }
        }
        return re;
    }

    @Override
    protected void onPostExecute(SwaggerAsyncResponse<R> r) {
        if(r.response != null){
            this.resultListener.onComplete(r.response);
            super.onPostExecute(r);
        }else {
            this.resultListener.onException(r.err);
        }

    }
}
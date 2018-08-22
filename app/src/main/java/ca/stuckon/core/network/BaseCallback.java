package ca.stuckon.core.network;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public abstract class BaseCallback<T> implements Callback<T> {

    protected abstract void onSuccess(T body);

    private BaseResponseListener listener;

    protected BaseCallback(BaseResponseListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            onSuccess(response.body());
        } else if (response.errorBody() != null) {
            onErrorResponse(response.code(), response.errorBody(), retrofit);
        } else {
            notifyError();
        }
    }

    protected void onErrorResponse(int code, ResponseBody errorBody, Retrofit retrofit) {
        notifyError();
    }

    @Override
    public void onFailure(Throwable t) {
        if (t instanceof IOException) {
            listener.onNoConnection(t);
        } else {
            listener.onResponseError(t);
        }
    }

    protected void notifyError() {
        listener.onResponseError(new IOException(""));
    }
}

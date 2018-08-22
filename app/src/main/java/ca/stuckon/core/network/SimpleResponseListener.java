package ca.stuckon.core.network;

public interface SimpleResponseListener<T> extends BaseResponseListener {
    void onSuccess(T response);
}

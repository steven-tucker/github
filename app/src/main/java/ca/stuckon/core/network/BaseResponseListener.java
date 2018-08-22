package ca.stuckon.core.network;

public interface BaseResponseListener {
    void onNoConnection(Throwable t);
    void onResponseError(Throwable t);
}

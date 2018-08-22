package ca.stuckon.core.network;


public class SimpleCallback<T> extends BaseCallback<T> {

    private SimpleResponseListener<T> listener;

    public SimpleCallback(SimpleResponseListener<T> listener) {
        super(listener);
        this.listener = listener;
    }

    @Override
    protected void onSuccess(T body) {
        notifySuccess(body);
    }

    protected void notifySuccess(T body) {
        listener.onSuccess(body);
    }
}

package ua.nure.easygo.rest;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oleg on 27.10.2016.
 */

public class BaseCall<T> implements Call<T> {

    private T body;

    public BaseCall(T body) {

        this.body = body;
    }

    /**
     * Failed
     */
    public BaseCall() {

    }

    @Override
    public Response<T> execute() throws IOException {

        if (body == null) {
            return Response.error(404, ResponseBody.create(MediaType.parse("application.json"), ""));
        }
        return Response.success(body);
    }

    @Override
    public void enqueue(Callback<T> callback) {
        if (body != null) {
            callback.onResponse(this, Response.success(body));
        } else {
            callback.onFailure(this, null);
        }

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {

        return null;
    }
}

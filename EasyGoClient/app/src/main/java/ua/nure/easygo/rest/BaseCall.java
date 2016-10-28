package ua.nure.easygo.rest;

import java.io.IOException;

import okhttp3.Request;
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

    @Override
    public Response<T> execute() throws IOException {

        return null;
    }

    @Override
    public void enqueue(Callback<T> callback) {
        callback.onResponse(this, Response.success(body));
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

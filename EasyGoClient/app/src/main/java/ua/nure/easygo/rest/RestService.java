package ua.nure.easygo.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.nure.easygo.model.attributes.AttributeValues;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 27.10.2016.
 */

public class RestService {

    private final static boolean USE_MOCK = false;
    private static String token = "";
    private static EasyGoService service;

    public synchronized static EasyGoService get() {
        if (service == null) {
            if (USE_MOCK) {
                // service = new MockService();
            } else {
                //TODO: replace url
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Token", token).build();
                                return chain.proceed(request);
                            }
                        })
                        .build();

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .registerTypeHierarchyAdapter(MapAttributes.class, new TypeAdapter<MapAttributes>() {
                            @Override
                            public void write(JsonWriter out, MapAttributes value) throws IOException {

                                out.value(new Gson().toJson(value));
                            }

                            @Override
                            public MapAttributes read(JsonReader in) throws IOException {
                                JsonToken t = in.peek();
                                if (t == JsonToken.STRING) {
                                    String s = in.nextString();
                                    return new Gson().fromJson(s, MapAttributes.class);
                                } else {
                                    return new MapAttributes();
                                }
                            }
                        })
                        .registerTypeHierarchyAdapter(AttributeValues.class, new TypeAdapter<AttributeValues>() {
                            @Override
                            public void write(JsonWriter out, AttributeValues value) throws IOException {
                                //out.jsonValue('\'' + new Gson().toJson(value) + '\'');
                                out.value(new Gson().toJson(value));
                            }

                            @Override
                            public AttributeValues read(JsonReader in) throws IOException {
                                JsonToken t = in.peek();
                                if (t == JsonToken.STRING) {
                                    String s = in.nextString();
                                    return new Gson().fromJson(s, AttributeValues.class);
                                } else {
                                    return null;
                                }
                            }
                        })
                        .create();

                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .baseUrl("http://192.168.43.13:8080/").build();

                service = retrofit.create(EasyGoService.class);
            }
        }
        return service;
    }
}

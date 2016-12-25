package ua.nure.easygo.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.nure.easygo.LoginHelper;
import ua.nure.easygo.model.attributes.AttributeValues;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 27.10.2016.
 */

public class RestService {


    static String SERVER_URL;
    private static String token = "";
    private static EasyGoService service;

    public static boolean authorised(Context context) {
        //return token != "";
        return LoginHelper.getInstance().getLogin(context) != "";
    }

    public static void init(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SERVER_URL = preferences.getString("editIPPref", "http://ec2-35-161-67-42.us-west-2.compute.amazonaws.com:8080/");
        if (service == null) {

            //File httpCacheDirectory = new File(context.getCacheDir(), "responses");


            //TODO: replace url
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Token", token)
                                    .build();
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
                    .baseUrl(SERVER_URL).build();

            service = retrofit.create(EasyGoService.class);
        }

    }

    public synchronized static EasyGoService get() {

        return service;
    }

    public static void authorise(String token) {
        RestService.token = token;

    }


}

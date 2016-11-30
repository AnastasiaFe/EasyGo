package ua.nure.easygo.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Oleg on 27.11.2016.
 */

public class ImageService {
    private static ImageService instance;
    private OkHttpClient client;

    private ImageService() {
        client = new OkHttpClient();

    }

    public synchronized static ImageService getInstance() {
        if (instance == null) {
            instance = new ImageService();
        }
        return instance;
    }

    public void uploadBitmap(Bitmap bmp, String path) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

                .addFormDataPart("img", "picture",
                        RequestBody.create(MediaType.parse("image/png"), byteArray))
                .build();

        Request request = new Request.Builder()
                .post(requestBody)
                .url(RestService.SERVER_URL + "image/" + path)
                .build();






        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public void getBitmap(String image, final BitmapCallback callback) {
        Request request = new Request.Builder().url(RestService.SERVER_URL + "image/" + image).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.consumeBitmap(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                callback.consumeBitmap(bmp);
            }
        });
    }

    public interface BitmapCallback {
        void consumeBitmap(Bitmap bmp);
    }
}

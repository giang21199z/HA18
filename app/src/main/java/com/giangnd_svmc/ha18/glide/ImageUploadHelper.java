package com.giangnd_svmc.ha18.glide;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ken on 10/04/2016.
 */
public class ImageUploadHelper {

    private Context context;
    private static final String UPLOAD_URL = "http://pizzaviet.vn/smac/edtech/upload.php";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private String name = "test";
    private Handler handler;
    OkHttpClient client;

    public ImageUploadHelper(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void uploadImage(final Bitmap bitmap) {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(context, "Processing...", "Please wait...", false, false);
        String encodedImage = getStringImage(bitmap);

        RequestBody requestBody = new FormBody.Builder().add(KEY_IMAGE, encodedImage).build();
        Request request = new Request.Builder()
                .url(UPLOAD_URL)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("fail", "fail");
                loading.dismiss();
                Message m = Message.obtain();
                m.setTarget(handler);
                m.what = 0;
                m.sendToTarget();
//
//                m.what = 1;
//                Bundle b = new Bundle();
//                b.putString("json","['success', [1]]");
//                m.setData(b);
//                m.sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                loading.dismiss();
                Message m = Message.obtain();
                m.setTarget(handler);
                m.what = 1;
                Bundle b = new Bundle();
                b.putString("json", response.body().string());
                m.setData(b);
                m.sendToTarget();
//                Log.d("success", response.body().string());
            }
        });
    }
}

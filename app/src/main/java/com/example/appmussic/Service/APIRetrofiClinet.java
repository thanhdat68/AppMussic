package com.example.appmussic.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofiClinet {

    private static Retrofit retrofit = null;

    //tra về cấu hình
    public static Retrofit getClinet(String base_url) {
        //cầu hình cho lên quan retrofit
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)        //ngắt kết nối khi đợi quá lâu
                .retryOnConnectionFailure(true)                      //khi có lỗi mãng thì sẽ cố kết nối lại
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        //thay vì phải đọc thì ta dùng biết này
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient) //cấu hình các phương thức
                .addConverterFactory(GsonConverterFactory.create(gson)) //conver các hằng biến của java
                .build();

        return retrofit;
    }
}

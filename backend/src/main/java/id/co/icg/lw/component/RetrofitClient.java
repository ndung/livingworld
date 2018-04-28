package id.co.icg.lw.component;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient<T> {
    private String baseUrl;

    private Retrofit.Builder builder;

    private Retrofit retrofit;

    private static OkHttpClient.Builder httpClient;

    private static HttpLoggingInterceptor logging;

    protected T service;


    public RetrofitClient(String baseUrl, Class<T> clazz) {
        this.baseUrl = baseUrl;
        builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        httpClient = new OkHttpClient.Builder();
        logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        service = retrofit.create(clazz);
    }
}

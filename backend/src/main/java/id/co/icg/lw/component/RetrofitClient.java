package id.co.icg.lw.component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;


public class RetrofitClient<T> {

    private Retrofit.Builder builder;

    private Retrofit retrofit;

    private static OkHttpClient.Builder httpClient;

    private static HttpLoggingInterceptor logging;

    protected T service;


    public RetrofitClient(String baseUrl) {
        init(baseUrl);

    }
    public RetrofitClient(String baseUrl, Class<T> clazz) {
        init(baseUrl);
        service = createService(clazz, null);
    }

    public RetrofitClient(String baseUrl, Class<T> tClass, Map<String, String> header) {
        init(baseUrl);
        service = createService(tClass, header);
    }

    private void init(String baseUrl) {
        builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        httpClient = new OkHttpClient.Builder();
        logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    private <T> T createService(Class<T> serviceClass, Map<String, String> headers) {
        if (headers != null) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder builder1 = original.newBuilder();
                for (String key : headers.keySet()) {
                    builder1 = builder1.header(key, headers.get(key));
                }
                Request request = builder1.build();
                return chain.proceed(request);
            });
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    protected void setHeader(Class<T> clazz, Map<String, String> header) {
        service = createService(clazz, header);
    }
}

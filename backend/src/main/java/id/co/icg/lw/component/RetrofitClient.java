package id.co.icg.lw.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RetrofitClient<T> {

    protected Retrofit.Builder builder;

    protected Retrofit retrofit;

    protected OkHttpClient.Builder httpClient;

    protected HttpLoggingInterceptor logging;

    protected T service;

    public RetrofitClient() {

    }

    public RetrofitClient(String baseUrl, Class<T> clazz, String proxyHost, int proxyPort) {
        init(baseUrl, proxyHost, proxyPort);
        service = createService(clazz, null);
    }

    protected void init(String baseUrl){
        init(baseUrl, null, 0);
    }

    protected void init(String baseUrl, String proxyHost, int proxyPort) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Proxy proxy = null;
        if (proxyHost!=null && !proxyHost.isEmpty()) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        }
        builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson));
        retrofit = builder.build();
        httpClient = new OkHttpClient.Builder();
        if (proxy!=null){
            httpClient = new OkHttpClient.Builder().proxy(proxy);
        }
        logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    protected <T> T createService(Class<T> serviceClass, Map<String, String> headers) {
        httpClient.interceptors().clear();
        httpClient.addInterceptor(logging);
        if (headers != null) {
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder builder1 = original.newBuilder();
                for (String key : headers.keySet()) {
                    builder1 = builder1.header(key, headers.get(key));
                }
                Request request = builder1.build();
                return chain.proceed(request);
            });
        }
        OkHttpClient client = httpClient
                .connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();
        builder.client(client);
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    protected void setHeader(Class<T> clazz, Map<String, String> header) {
        service = createService(clazz, header);
    }

}

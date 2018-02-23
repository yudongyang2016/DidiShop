package com.shop.didi.didishop.http;

import android.util.Log;

import com.shop.didi.didishop.MyApplication;
import com.shop.didi.didishop.utils.AppConfig;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author ydy
 *         modify on 2017/11/8
 */
public class HttpUtil {

    /**
     * 缓存有效期：两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    @SuppressWarnings("unused")
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    @SuppressWarnings("unused")
    public static final String CACHE_CONTROL_AGE = "max-age=0";
//    /**
//     * 云端响应头拦截器，用来配置缓存策略
//     * Dangerous interceptor that rewrites the server's cache-control header.
//     */
//    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetWorkUtil.isNetworkAvailable()) {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)//仅仅使用缓存
//                        .build();
//                TLog.d("no network");
//            }
//            Response originalResponse = chain.proceed(request);
//            if (NetWorkUtil.isNetworkAvailable()) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            } else {
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    };

    /**
     * ApiService
     */
    private ApiService apiService;

    /**
     * retrofit初始化
     * apiService创建
     */
    private HttpUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 获取HttpUtil实例
     */
    public static HttpUtil getInstance() {
        return HttpUtilSingleton.INSTANCE;
    }

    /**
     * 静态内部类方式创建HttpUtil实例
     */
    private static class HttpUtilSingleton {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * api service
     */
    public ApiService getApiService() {
        return apiService;
    }

    /**
     * OkHttpClient单例
     */
    private static OkHttpClient getOkHttpClient() {
        return OkHttpClientSingleton.OK_HTTP_CLIENT;
    }

    /**
     * 静态内部类方式创建OkHttpClient单例
     */
    private static class OkHttpClientSingleton {
        static Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .cache(cache)
                //建立连接的超时时长
                .connectTimeout(180, TimeUnit.SECONDS)
                //传递数据的超时时长
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
//                .addInterceptor(mRewriteCacheControlInterceptor)
//                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(LOGGING_INTERCEPTOR)
                .build();
    }

    /**
     * 日志拦截器
     */
    private static final Interceptor LOGGING_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("TAG", String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("TAG", String.format(Locale.getDefault(),
                    "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
}

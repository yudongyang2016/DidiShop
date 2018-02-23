package com.shop.didi.didishop.mvp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.didi.didishop.base.BaseResponse;
import com.shop.didi.didishop.http.ApiService;
import com.shop.didi.didishop.mvp.AsyncCallBack;
import com.shop.didi.didishop.utils.AppConfig;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpDao {

    private static final String TAG = "TAG";
    private static HttpDao mHttpDao = null;

    private HttpDao() {

    }

    public static HttpDao getInstance() {
        if (mHttpDao == null) {
            mHttpDao = new HttpDao();
        }
        return mHttpDao;
    }

    public ApiService getApiService() {
        //使用自定义转换器
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new DeserializerData()).create();
        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(AppConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return mRetrofit.create(ApiService.class);
    }

    public Observer<BaseResponse> createObserver(final AsyncCallBack callBack) {
        Observer<BaseResponse> observer = new Observer<BaseResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse response) {
                if (response.getState() != 0) {
                    //统一处理
                }
            }

            @Override
            public void onError(Throwable e) {
//                BaseResponse response = new BaseResponse();
//                response.setState(2500);
//                response.setMsg("网络错误，请检查你的网络");
//                response.setData(null);
//                callBack.onLoadError(response);
            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }

}

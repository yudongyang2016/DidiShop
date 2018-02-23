package com.shop.didi.didishop.http;


import com.shop.didi.didishop.base.BaseResponse;
import com.shop.didi.didishop.mvp.AsyncCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ydy
 *         modify on 2017/11/8
 *         Create a singleton using a static inner class
 */
@SuppressWarnings("unused")
public class HttpDao {

    private HttpDao() {
    }

    public static HttpDao getInstance() {
        return HttpDaoSingleton.HTTP_DAO;
    }

    private static class HttpDaoSingleton {
        private static final HttpDao HTTP_DAO = new HttpDao();
    }

    @SuppressWarnings("unused")
    public Callback<BaseResponse> createCallback(final AsyncCallBack callBack) {
        return new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    BaseResponse baseResponse = response.body();
                    if (baseResponse.getState() == 1) {
                        callBack.onLoadSuccess(baseResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        };
    }

}

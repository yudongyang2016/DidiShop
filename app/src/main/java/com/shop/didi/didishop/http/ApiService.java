package com.shop.didi.didishop.http;

import com.shop.didi.didishop.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author: ydy
 * Created: 2017/7/13 15:55
 * Description:
 */

public interface ApiService {

    @GET("")
    Observable<BaseResponse> login();

    @GET("")
    Observable<BaseResponse> register();

}

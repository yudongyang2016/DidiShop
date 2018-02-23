package com.shop.didi.didishop.mvp.model.impl;

import android.util.Log;

import com.shop.didi.didishop.base.BaseResponse;
import com.shop.didi.didishop.http.HttpUtil;
import com.shop.didi.didishop.mvp.AsyncCallBack;
import com.shop.didi.didishop.mvp.model.HttpDao;
import com.shop.didi.didishop.mvp.model.ILoginModel;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: ydy
 * Created: 2017/7/13 16:59
 * Description:
 */

public class LoginModel implements ILoginModel {

    @Override
    public void login(final AsyncCallBack callBack) {
        HttpUtil.getInstance()
                .getApiService()
                .register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseResponse registerResponse) throws Exception {
                        //根据注册的响应结果去做一些操作
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseResponse, ObservableSource<BaseResponse>>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(@io.reactivex.annotations.NonNull BaseResponse registerResponse) throws Exception {
                        return  HttpUtil.getInstance().getApiService().login();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseResponse loginResponse) throws Exception {
                        Log.i("TAG", "登录成功");
                        callBack.onLoadSuccess(loginResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        Log.i("TAG", "网络异常");
                    }
                });
    }

    @Override
    public void register(AsyncCallBack callBack) {
        HttpUtil.getInstance()
                .getApiService()
                .register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(HttpDao.getInstance().createObserver(callBack));
        //这是统一去处理的方式!!!
    }

}

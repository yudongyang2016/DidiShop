package com.shop.didi.didishop.mvp.presenter.impl;

import com.shop.didi.didishop.base.BasePresenterImpl;
import com.shop.didi.didishop.mvp.AsyncCallBack;
import com.shop.didi.didishop.mvp.model.ILoginModel;
import com.shop.didi.didishop.mvp.model.impl.LoginModel;
import com.shop.didi.didishop.mvp.presenter.contract.LoginContract;

/**
 * Author: ydy
 * Created: 2017/7/13 19:30
 * Description:
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    private final ILoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    @Override
    public void login() {
        loginModel.login(new AsyncCallBack() {
            @Override
            public void onLoadSuccess(Object object) {

            }

            @Override
            public void onLoadError(Object error) {

            }
        });
    }

    @Override
    public void register() {
        loginModel.register(new AsyncCallBack() {
            @Override
            public void onLoadSuccess(Object object) {

            }

            @Override
            public void onLoadError(Object error) {

            }
        });
    }
}

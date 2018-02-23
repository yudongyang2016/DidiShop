package com.shop.didi.didishop.activity;

import android.os.Bundle;

import com.shop.didi.didishop.R;
import com.shop.didi.didishop.base.BaseActivity;
import com.shop.didi.didishop.base.BaseResponse;
import com.shop.didi.didishop.mvp.presenter.contract.LoginContract;
import com.shop.didi.didishop.mvp.presenter.impl.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter, BaseResponse> implements LoginContract.View{


    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.attachView(this, this);
        mPresenter.login();
        mPresenter.register();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

}

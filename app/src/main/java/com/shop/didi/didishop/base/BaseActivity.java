package com.shop.didi.didishop.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.shop.didi.didishop.activity.LoginActivity;
import com.shop.didi.didishop.mvp.presenter.IBasePresenter;
import com.shop.didi.didishop.mvp.view.IView;
import com.shop.didi.didishop.utils.AppManager;
import com.shop.didi.didishop.utils.DialogUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends IBasePresenter, V> extends AppCompatActivity implements IView<V> {


    public T mPresenter;
    public DialogUtils mWaitingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (null != getPresenter()) {
            mPresenter = getPresenter();
        }
        initData(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        // 始终竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * Base基本类
     */
    public abstract int getLayoutId();

    /**
     * 获取Presenter
     */
    public T getPresenter() {
        return null;
    }


    /**
     * 跳转到登陆界面
     */
    public void goToLogin() {
        startActivityForResult(new Intent(this, LoginActivity.class), 200);
    }

    public void launcher(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 携带数据的页面跳转
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        AppManager.getAppManager().removeActivity(this);
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
        }
    }


    protected final void onFinish() {
        closeInputMethod();
        this.finish();
    }

    /**
     * 收起软键盘
     */
    public void closeInputMethod() {
        // 收起键盘
        View view = getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示加载弹框
     */
    @Override
    public void showProgress() {
        if (mWaitingDialog == null) {
            mWaitingDialog = new DialogUtils(getApplicationContext());
        }
        mWaitingDialog.show();
//        mWaitingDialog.title.setVisibility(View.GONE);
    }

    /**
     * 隐藏加载弹框
     */
    @Override
    public void hideProgress() {
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
        }
    }


    /**
     * 加载数据失败
     */
    @Override
    public void showDataError(String errorMessage, int tag) {
        hideProgress();
    }

    /**
     * 加载数据成功
     */
    @Override
    public void showDataSuccess(V data) {
        hideProgress();
    }

    @Override
    public void showEmptyView(String msg) {

    }

    @Override
    public void showNetErrorView() {

    }
}

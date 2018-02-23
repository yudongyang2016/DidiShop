package com.shop.didi.didishop.base;

import android.content.Context;
import android.widget.Toast;

import com.shop.didi.didishop.mvp.presenter.IBasePresenter;
import com.shop.didi.didishop.mvp.view.IView;
import com.shop.didi.didishop.utils.NetWorkUtil;

public class BasePresenterImpl<V extends IView> implements IBasePresenter {


    public V mView;
    public Context context;

    @Override
    public boolean checkNetWork(Context context) {
        if (!NetWorkUtil.isNetworkAvailable()) {
            Toast.makeText(context, "网络不给力", Toast.LENGTH_SHORT).show();
        }
        return NetWorkUtil.isNetworkAvailable();
    }

    public void attachView(V view, Context context) {
        this.mView = view;
        this.context = context;
    }

}

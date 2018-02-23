package com.shop.didi.didishop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.didi.didishop.mvp.presenter.IBasePresenter;
import com.shop.didi.didishop.mvp.view.IView;
import com.shop.didi.didishop.utils.DialogUtils;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends IBasePresenter, V> extends Fragment implements IView<V> {

    protected View mRootView;
    protected T mPresenter;
    public DialogUtils mWaitingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);
            ButterKnife.bind(this, mRootView);
            if (null != getPresenter()) {
                mPresenter = getPresenter();
            }
            initView(mRootView);
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    /**
     * 数据加载失败
     */
    @Override
    public void showDataError(String errorMessage, int tag) {
        hideProgress();
    }

    /**
     * 数据加载成功
     */
    @Override
    public void showDataSuccess(V data) {
        hideProgress();
    }

    /**
     * 显示加载进度条
     */
    @Override
    public void showProgress() {
        if (mWaitingDialog == null) {
            mWaitingDialog = new DialogUtils(getContext());
        }
        mWaitingDialog.show();
        mWaitingDialog.title.setVisibility(View.GONE);
    }

    /**
     * 隐藏加载进度条
     */
    @Override
    public void hideProgress() {
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
        }
    }

    protected abstract int getContentLayout();

    public T getPresenter() {
        return null;
    }

    protected abstract void initData();

    protected abstract void initView(View RootView);

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
        }
    }
}

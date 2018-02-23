package com.shop.didi.didishop.mvp.view;

/**
 * Author: ydy
 * Created: 2017/7/13 16:59
 * Description:
 */

public interface IView<V> {

    /**
     * 数据加载失败
     */
    void showDataError(String errorMessage, int tag);

    /**
     * 数据加载成功
     */
    void showDataSuccess(V data);

    /**
     * 显示加载进度
     */
    void showProgress();

    /**
     * 隐藏加载进度
     */
    void hideProgress();

    /**
     * 显示无网络视图
     */
    void showNetErrorView();

    /**
     * 显示没有加载到内容的视图
     */
    void showEmptyView(String msg);

}

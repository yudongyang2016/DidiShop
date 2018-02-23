package com.shop.didi.didishop.mvp;

/**
 * Created by ydy on 2017/7/13
 */

public interface AsyncCallBack {
    /**
     * 回调成功的数据
     * @param object
     */
    void onLoadSuccess(Object object);

    /**+
     * 回调失败
     * @param error
     */
    void onLoadError(Object error);
}

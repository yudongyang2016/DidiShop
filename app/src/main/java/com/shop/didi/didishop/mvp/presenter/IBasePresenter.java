package com.shop.didi.didishop.mvp.presenter;

import android.content.Context;

/**
 * Created by Administrator on 2016/11/24 0024.
 * APP第一个界面presenter层接口
 */

public interface IBasePresenter {
    /**
     * 判断网络连接
     * */
    boolean checkNetWork(Context context);
}

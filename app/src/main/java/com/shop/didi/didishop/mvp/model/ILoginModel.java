package com.shop.didi.didishop.mvp.model;

import com.shop.didi.didishop.mvp.AsyncCallBack;

/**
 * Author: ydy
 * Created: 2017/7/13 16:58
 * Description:
 */

public interface ILoginModel {

    void login(AsyncCallBack callBack);

    void register(AsyncCallBack callBack);
}

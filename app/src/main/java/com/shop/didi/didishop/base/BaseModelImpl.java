package com.shop.didi.didishop.base;


import com.shop.didi.didishop.http.HttpUtil;
import com.shop.didi.didishop.utils.NetWorkUtil;

public class BaseModelImpl {

    public String getCache() {
        return NetWorkUtil.isNetworkAvailable() ? HttpUtil.CACHE_CONTROL_AGE : HttpUtil.CACHE_CONTROL_CACHE;
    }
}

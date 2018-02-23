package com.shop.didi.didishop.mvp.presenter.contract;

import com.shop.didi.didishop.base.BaseResponse;
import com.shop.didi.didishop.mvp.view.IView;

/**
 * Author: ydy
 * Created: 2017/7/13 19:28
 * Description:
 */

public class LoginContract {

    public interface View extends IView<BaseResponse> {
        //自定义成功失败回调方法
    }

    public interface Presenter {

        void login();

        void register();
    }

}

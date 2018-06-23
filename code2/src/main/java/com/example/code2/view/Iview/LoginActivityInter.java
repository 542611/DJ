package com.example.code2.view.Iview;

import com.example.code2.model.bean.LoginBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface LoginActivityInter {

    void getLoginSuccess(LoginBean loginBean);


    void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}

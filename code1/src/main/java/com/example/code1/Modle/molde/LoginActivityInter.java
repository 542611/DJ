package com.example.code1.Modle.molde;

import com.example.code1.Modle.bean.LoginBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface LoginActivityInter {

    void getLoginSuccess(LoginBean loginBean);


    void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}

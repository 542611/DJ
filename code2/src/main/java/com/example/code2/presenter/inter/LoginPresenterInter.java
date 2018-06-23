package com.example.code2.presenter.inter;


import com.example.code2.model.bean.LoginBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface LoginPresenterInter {

    void onSuccess(LoginBean loginBean);


    void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}

package com.example.code1.View.view;

public interface IMainView {

    //获手机号
    String getMobile();

    //获取密码
    String getPassword();

    //登录成功
    void loginSuccess();

    //登录失败
    void loginError();
}

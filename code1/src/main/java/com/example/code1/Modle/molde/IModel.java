package com.example.code1.Modle.molde;

import java.util.Map;

public interface IModel {

    //    登录
    void login(String url, Map<String, String> params, LoginListener loginListener);

    //    注册
    void reg(String url, Map<String, String> params, RegListener regListener);

}

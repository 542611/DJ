package com.example.code1.Modle.molde;

import com.example.code1.Modle.util.HttpUtils;
import com.example.code1.Modle.bean.RegBean;
import com.example.code1.Modle.bean.UserBean;
import com.google.gson.Gson;
import com.umeng.socialize.utils.Log;

import java.util.Map;

public class ModelImpl implements IModel {

    private static final String TAG = "ModelImpl----";

    @Override
    public void login(final String url, Map<String, String> params, final LoginListener loginListener) {
        //
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        //调用post请求
        httpUtils.okPost(url, params);
        //        设置回调监听
        httpUtils.setOkLoadListener(new OkLoadListener() {
            @Override
            public void okLoadSuccess(String json) {

                Log.d(TAG, "okLoadSuccess: " + json);

                //将数据传入p层
                //解析json  如果code是0，成功，否则失败
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(json, UserBean.class);
                if (userBean.getCode().equals("0")) {
                    loginListener.loginSuccess(json);
                } else {
                    loginListener.loginError(json);
                }

            }

            @Override
            public void okLoadError(String error) {
                loginListener.loginError(error);
            }
        });
    }

    //注册
    @Override
    public void reg(String url, Map<String, String> params, final RegListener regListener) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.okPost(url, params);//调用post请求
//        设置回调监听
        httpUtils.setOkLoadListener(new OkLoadListener() {
            @Override
            public void okLoadSuccess(String json) {

                Log.d(TAG, "okLoadSuccess: " + json);

                //将数据传入p层
                //解析json  如果code是0，成功，否则失败
                Gson gson = new Gson();
                RegBean userBean = gson.fromJson(json, RegBean.class);
                if (userBean.getCode().equals("0")) {
                    regListener.regSuccess(json);
                } else {
                    regListener.regError(json);
                }

            }

            @Override
            public void okLoadError(String error) {
                regListener.regError(error);
            }
        });
    }

}

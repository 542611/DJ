package com.example.code1.Presenter;

import com.example.code1.Modle.molde.LoginActivityInter;
import com.example.code1.Modle.bean.LoginBean;
import com.example.code1.Modle.molde.LoginModel;
import com.example.code1.View.acticvity.MainActivity;

public class LoginPresenter implements LoginPresenterInter {

    private LoginModel loginModel;
    private LoginActivityInter loginActivityInter;

    public LoginPresenter(MainActivity mainActivity) {
        this.loginActivityInter = loginActivityInter;

        loginModel = new LoginModel(this);
    }
    //处理逻辑用的...判断
    public void getLogin(String loginUrl, String phone, String pwd) {

        loginModel.getLogin(loginUrl,phone,pwd);

    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        loginActivityInter.getLoginSuccess(loginBean);
    }

    @Override
    public void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl) {
        loginActivityInter.getLoginSuccessByQQ(loginBean,ni_cheng,iconurl);
    }

    public void getLoginByQQ(String phone, String pwd, String ni_cheng, String iconurl) {

        loginModel.getLoginByQQ(phone,pwd,ni_cheng,iconurl);

    }

}

package com.example.code2.presenter;

import com.example.code2.model.UserInfoModel;
import com.example.code2.model.bean.UserInfoBean;
import com.example.code2.presenter.inter.UserInfoPresenterInter;
import com.example.code2.view.Iview.UserInforInter;

/**
 * Created by Dash on 2018/2/23.
 */
public class UserInfoPresenter implements UserInfoPresenterInter {

    private final UserInfoModel userInfoModel;
    private final UserInforInter userInforInter;

    public UserInfoPresenter(UserInforInter userInforInter) {
        this.userInforInter = userInforInter;
        userInfoModel = new UserInfoModel(this);
    }

    public void getUserInfo(String userInfoUrl, String uid) {

        userInfoModel.getUserInfo(userInfoUrl,uid);

    }

    @Override
    public void onUserInfoSUccess(UserInfoBean userInfoBean) {
        userInforInter.onUserInforSuccess(userInfoBean);
    }
}

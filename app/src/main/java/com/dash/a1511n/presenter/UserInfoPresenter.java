package com.dash.a1511n.presenter;

import com.dash.a1511n.model.UserInfoModel;
import com.dash.a1511n.model.bean.UserInfoBean;
import com.dash.a1511n.presenter.inter.UserInfoPresenterInter;
import com.dash.a1511n.view.Iview.UserInforInter;
import com.dash.a1511n.view.activity.UserInfoActivity;

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

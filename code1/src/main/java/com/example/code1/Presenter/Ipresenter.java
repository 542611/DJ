package com.example.code1.Presenter;

import com.example.code1.Modle.molde.IModel;
import com.example.code1.View.view.IMainView;
import com.example.code1.View.view.IRegView;

public interface Ipresenter {

    //    登录
    void loginPresenter(IModel iModel, IMainView iMainView);

    //    注册
    void regPresenter(IModel iModel, IRegView iRegView);

}

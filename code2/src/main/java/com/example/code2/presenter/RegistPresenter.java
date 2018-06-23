package com.example.code2.presenter;

import com.example.code2.model.RegistModel;
import com.example.code2.model.bean.RegistBean;
import com.example.code2.presenter.inter.RegistPresenterInter;
import com.example.code2.view.Iview.RegistActivityInter;

/**
 * Created by Dash on 2018/2/2.
 */
public class RegistPresenter implements RegistPresenterInter {

    private RegistActivityInter registActivityInter;
    private RegistModel registModel;

    public RegistPresenter(RegistActivityInter registActivityInter) {
        this.registActivityInter = registActivityInter;
        registModel = new RegistModel(this);
    }

    public void registUser(String registUrl, String name, String pwd) {

        registModel.registUser(registUrl,name,pwd);
    }

    @Override
    public void onRegistSuccess(RegistBean registBean) {
        registActivityInter.onRegistSuccess(registBean);
    }
}

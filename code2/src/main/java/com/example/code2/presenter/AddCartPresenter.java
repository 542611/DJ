package com.example.code2.presenter;

import com.example.code2.model.AddCartModel;
import com.example.code2.model.bean.AddCartBean;
import com.example.code2.presenter.inter.AddCartPresenterInter;
import com.example.code2.view.Iview.ActivityAddCartInter;

/**
 * Created by Dash on 2018/2/1.
 */
public class AddCartPresenter implements AddCartPresenterInter {

    private ActivityAddCartInter activityAddCartInter;
    private AddCartModel addCartModel;

    public AddCartPresenter(ActivityAddCartInter activityAddCartInter) {
        this.activityAddCartInter = activityAddCartInter;

        addCartModel = new AddCartModel(this);
    }

    public void addToCart(String addCart, String uid, int pid) {

        addCartModel.addToCart(addCart,uid,pid);

    }

    @Override
    public void onCartAddSuccess(AddCartBean addCartBean) {
        activityAddCartInter.onCartAddSuccess(addCartBean);
    }
}

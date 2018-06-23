package com.example.code2.presenter;

import com.example.code2.model.CartModel;
import com.example.code2.model.bean.CartBean;
import com.example.code2.presenter.inter.CartPresenterInter;
import com.example.code2.view.Iview.FragmentCartInter;

/**
 * Created by Dash on 2018/1/30.
 */
public class FragmentCartPresenter implements CartPresenterInter {

    private FragmentCartInter fragmentCartInter;
    private CartModel cartModel;

    public FragmentCartPresenter(FragmentCartInter fragmentCartInter) {
        this.fragmentCartInter = fragmentCartInter;

        cartModel = new CartModel(this);
    }

    public void getCartData(String selectCart, String uid) {

        cartModel.getCartData(selectCart,uid);

    }

    @Override
    public void getCartDataNull() {
        fragmentCartInter.getCartDataNull();
    }

    @Override
    public void getCartDataSuccess(CartBean cartBean) {
        fragmentCartInter.getCartDataSuccess(cartBean);
    }
}

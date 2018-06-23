package com.example.code2.presenter.inter;


import com.example.code2.model.bean.CartBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface CartPresenterInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}

package com.example.code2.view.Iview;

import com.example.code2.model.bean.CartBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface FragmentCartInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}

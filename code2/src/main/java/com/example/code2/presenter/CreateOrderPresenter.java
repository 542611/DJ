package com.example.code2.presenter;

import com.example.code2.model.CreateOrderModel;
import com.example.code2.model.bean.CreateOrderBean;
import com.example.code2.presenter.inter.CreateOrderPresenterInter;
import com.example.code2.view.Iview.CreateOrderInter;

/**
 * Created by Dash on 2018/2/25.
 */
public class CreateOrderPresenter implements CreateOrderPresenterInter {

    private CreateOrderInter createOrderInter;
    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter(CreateOrderInter createOrderInter) {
        this.createOrderInter = createOrderInter;
        createOrderModel = new CreateOrderModel(this);
    }

    public void createOrder(String createOrderUrl, String uid, double price) {

        createOrderModel.createOrder(createOrderUrl,uid,price);

    }

    @Override
    public void onOrderCreateSuccess(CreateOrderBean createOrderBean) {
        createOrderInter.onCreateOrderSuccess(createOrderBean);
    }
}

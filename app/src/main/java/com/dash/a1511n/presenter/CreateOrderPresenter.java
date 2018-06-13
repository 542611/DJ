package com.dash.a1511n.presenter;

import com.dash.a1511n.model.CreateOrderModel;
import com.dash.a1511n.model.bean.CreateOrderBean;
import com.dash.a1511n.presenter.inter.CreateOrderPresenterInter;
import com.dash.a1511n.view.Iview.CreateOrderInter;
import com.dash.a1511n.view.activity.MakeSureOrderActivity;

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

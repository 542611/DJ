package com.dash.a1511n.model;

import com.dash.a1511n.model.bean.CreateOrderBean;
import com.dash.a1511n.presenter.CreateOrderPresenter;
import com.dash.a1511n.presenter.inter.CreateOrderPresenterInter;
import com.dash.a1511n.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Dash on 2018/2/25.
 */
public class CreateOrderModel {
    private CreateOrderPresenterInter createOrderPresenterInter;

    public CreateOrderModel(CreateOrderPresenterInter createOrderPresenterInter) {
        this.createOrderPresenterInter = createOrderPresenterInter;
    }

    public void createOrder(String createOrderUrl, String uid, double price) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("price", String.valueOf(price));

        OkHttp3Util_03.doPost(createOrderUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String json = response.body().string();

                    CreateOrderBean createOrderBean = new Gson().fromJson(json,CreateOrderBean.class);

                    createOrderPresenterInter.onOrderCreateSuccess(createOrderBean);

                }

            }
        });

    }
}

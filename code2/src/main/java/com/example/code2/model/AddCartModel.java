package com.example.code2.model;
import com.example.code2.model.bean.AddCartBean;
import com.example.code2.presenter.inter.AddCartPresenterInter;
import com.example.code2.util.CommonUtils;
import com.example.code2.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Dash on 2018/2/1.
 */
public class AddCartModel {


    private AddCartPresenterInter addCartPresenterInter;

    public AddCartModel(AddCartPresenterInter addCartPresenterInter) {
        this.addCartPresenterInter = addCartPresenterInter;
    }

    public void addToCart(String addCart, String uid, int pid) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("pid", String.valueOf(pid));

        OkHttp3Util_03.doPost(addCart, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    final AddCartBean addCartBean = new Gson().fromJson(json,AddCartBean.class);

                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            addCartPresenterInter.onCartAddSuccess(addCartBean);
                        }
                    });

                }
            }
        });

    }
}

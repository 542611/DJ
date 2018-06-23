package com.example.code2.model;

import com.example.code2.model.bean.ChildFenLeiBean;
import com.example.code2.presenter.inter.FenLeiRightPresenterInter;
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
 * Created by Dash on 2018/1/25.
 */
public class FragmentFenLeiRightModel {

    private FenLeiRightPresenterInter fenLeiRightPresenterInter;

    public FragmentFenLeiRightModel(FenLeiRightPresenterInter fenLeiRightPresenterInter) {
        this.fenLeiRightPresenterInter = fenLeiRightPresenterInter;
    }

    public void getChildData(String childFenLeiUrl, int cid) {

        Map<String, String> params = new HashMap<>();
        params.put("cid", String.valueOf(cid));

        OkHttp3Util_03.doPost(childFenLeiUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    //解析
                    final ChildFenLeiBean childFenLeiBean = new Gson().fromJson(json,ChildFenLeiBean.class);

                    //主线程
                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {

                            fenLeiRightPresenterInter.onSuncess(childFenLeiBean);

                        }
                    });

                }
            }
        });

    }
}

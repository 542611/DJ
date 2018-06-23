package com.example.code5.modle.modle;

import com.example.code5.modle.GetNewsListener;
import com.example.code5.modle.http.HttpUtils;
import com.example.code5.modle.http.OkLoadListener;

import java.util.Map;

public class ModelImpl implements IModel {

    @Override
    public void getNewsData(String url, Map<String, String> map, final GetNewsListener getNewsListener) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.okGet(url);
        httpUtils.setOkLoadListener(new OkLoadListener() {
            @Override
            public void okLoadSuccess(String json) {
                getNewsListener.getSuccess(json);
            }

            @Override
            public void okLoadError(String error) {
                getNewsListener.getError(error);
            }
        });
    }

}

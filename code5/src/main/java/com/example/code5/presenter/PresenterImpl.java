package com.example.code5.presenter;

import android.util.Log;

import com.example.code5.View.view.IMainView;
import com.example.code5.modle.GetNewsListener;
import com.example.code5.modle.bean.NewsBean;
import com.example.code5.modle.http.HttpConfig;
import com.example.code5.modle.modle.IModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresenterImpl implements IPresenter{

    private static final String TAG = "------PresenterImpl";
    @Override
    public void showNewsToView(IModel iModel, final IMainView iMainView) {
        Map<String,String> map = new HashMap<>();
        String url = HttpConfig.news_url+"?page=1";
        iModel.getNewsData(url, map, new GetNewsListener() {
            @Override
            public void getSuccess(String json) {
                Log.d(TAG, "成功: "+json);
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(json, NewsBean.class);
                List<NewsBean.DataBeanX.DataBean> list = newsBean.getData().getData();
                iMainView.showNews(list);
            }

            @Override
            public void getError(String error) {
                Log.d(TAG, "错误: "+error);
            }
        });
    }

}

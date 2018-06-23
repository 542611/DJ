package com.example.code5.modle.modle;

import com.example.code5.modle.GetNewsListener;

import java.util.Map;

public interface IModel {

    void getNewsData(String url, Map<String, String> map, GetNewsListener getNewsListener);

}

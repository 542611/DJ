package com.example.code1.Modle.molde;

public interface OkLoadListener {

    //    请求数据成功
    void okLoadSuccess(String json);

    //请求数据失败
    void okLoadError(String error);

}

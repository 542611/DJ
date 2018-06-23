package com.example.code2.presenter.inter;

import com.example.code2.model.bean.FenLeiBean;
import com.example.code2.model.bean.HomeBean;

/**
 * Created by Dash on 2018/1/23.
 */
public interface FragmentHomePInter {
    //首页的数据
    void onSuccess(HomeBean homeBean);
    //分类
    void onFenLeiDataSuccess(FenLeiBean fenLeiBean);
}

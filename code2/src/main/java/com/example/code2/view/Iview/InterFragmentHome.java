package com.example.code2.view.Iview;

import com.example.code2.model.bean.FenLeiBean;
import com.example.code2.model.bean.HomeBean;

/**
 * Created by Dash on 2018/1/23.
 */
public interface InterFragmentHome {
    void onSuccess(HomeBean homeBean);

    void onFenLeiDataSuccess(FenLeiBean fenLeiBean);
}

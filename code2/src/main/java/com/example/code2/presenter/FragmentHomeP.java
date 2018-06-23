package com.example.code2.presenter;

import com.example.code2.model.FragmentHomeModel;
import com.example.code2.model.bean.FenLeiBean;
import com.example.code2.model.bean.HomeBean;
import com.example.code2.presenter.inter.FragmentHomePInter;
import com.example.code2.view.Iview.InterFragmentHome;

/**
 * Created by Dash on 2018/1/23.
 */
public class FragmentHomeP implements FragmentHomePInter {

    private FragmentHomeModel fragmentHomeModel;
    private InterFragmentHome interFragmentHome;

    //创建构造方法
    public FragmentHomeP(InterFragmentHome interFragmentHome) {
        this.interFragmentHome = interFragmentHome;

        //创建model的引用
        fragmentHomeModel = new FragmentHomeModel(this);
    }

    public void getNetData(String homeUrl) {

        //让model获取数据
        fragmentHomeModel.getData(homeUrl);

    }

    @Override
    public void onSuccess(HomeBean homeBean) {

        //此时的数据回调到p层,,,把数据从p层传到view层进行使用
        interFragmentHome.onSuccess(homeBean);
    }

    @Override
    public void onFenLeiDataSuccess(FenLeiBean fenLeiBean) {
        interFragmentHome.onFenLeiDataSuccess(fenLeiBean);
    }

    public void getFenLeiData(String fenLeiUrl) {

        fragmentHomeModel.getFenLeiData(fenLeiUrl);
    }
}

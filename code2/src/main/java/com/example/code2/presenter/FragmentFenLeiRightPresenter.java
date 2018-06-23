package com.example.code2.presenter;

import com.example.code2.model.FragmentFenLeiRightModel;
import com.example.code2.model.bean.ChildFenLeiBean;
import com.example.code2.presenter.inter.FenLeiRightPresenterInter;
import com.example.code2.view.Iview.FenLeiRightInter;

/**
 * Created by Dash on 2018/1/25.
 */
public class FragmentFenLeiRightPresenter implements FenLeiRightPresenterInter {

    private FenLeiRightInter fenLeiRightInter;
    private FragmentFenLeiRightModel fragmentFenLeiRightModel;

    public FragmentFenLeiRightPresenter(FenLeiRightInter fenLeiRightInter) {
        this.fenLeiRightInter = fenLeiRightInter;

        fragmentFenLeiRightModel = new FragmentFenLeiRightModel(this);
    }

    public void getChildData(String childFenLeiUrl, int cid) {

        fragmentFenLeiRightModel.getChildData(childFenLeiUrl,cid);
    }

    @Override
    public void onSuncess(ChildFenLeiBean childFenLeiBean) {

        fenLeiRightInter.getSuccessChildData(childFenLeiBean);

    }
}

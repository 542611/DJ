package com.dash.a1511n.presenter;

import com.dash.a1511n.model.UpLoadPicModel;
import com.dash.a1511n.model.bean.UpLoadPicBean;
import com.dash.a1511n.presenter.inter.UpLoadPicPresenterInter;
import com.dash.a1511n.view.Iview.UpLoadActivityInter;
import com.dash.a1511n.view.activity.UserInfoActivity;

import java.io.File;

/**
 * Created by Dash on 2018/2/23.
 */
public class UpLoadPicPresenter implements UpLoadPicPresenterInter {

    private UpLoadPicModel upLoadPicModel;
    private UpLoadActivityInter upLoadActivityInter;

    public UpLoadPicPresenter(UpLoadActivityInter upLoadActivityInter) {
        this.upLoadActivityInter = upLoadActivityInter;
        upLoadPicModel = new UpLoadPicModel(this);
    }

    public void uploadPic(String uploadIconUrl, File saveIconFile, String uid, String fileName) {

        upLoadPicModel.uploadPic(uploadIconUrl,saveIconFile,uid,fileName);

    }

    @Override
    public void uploadPicSuccess(UpLoadPicBean upLoadPicBean) {
        upLoadActivityInter.uploadPicSuccess(upLoadPicBean);
    }
}

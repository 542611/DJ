package com.example.code5.presenter;

import com.example.code5.View.view.IMainView;
import com.example.code5.modle.modle.IModel;

public interface IPresenter {

    void showNewsToView(IModel iModel, IMainView iMainView);

}

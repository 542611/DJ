package com.example.code2.presenter;

import com.example.code2.model.ProductListModel;
import com.example.code2.model.bean.ProductListBean;
import com.example.code2.presenter.inter.ProductListPresenterInter;
import com.example.code2.view.Iview.ProductListActivityInter;

/**
 * Created by Dash on 2018/1/26.
 */
public class ProductListPresenter implements ProductListPresenterInter {

    private ProductListModel productListModel;
    private ProductListActivityInter productListActivityInter;

    public ProductListPresenter(ProductListActivityInter productListActivityInter) {
        this.productListActivityInter = productListActivityInter;

        productListModel = new ProductListModel(this);
    }

    public void getProductData(String seartchUrl, String keywords, int page) {

        productListModel.getProductData(seartchUrl,keywords,page);
    }

    @Override
    public void onSuccess(ProductListBean productListBean) {
        productListActivityInter.getProductDataSuccess(productListBean);
    }
}

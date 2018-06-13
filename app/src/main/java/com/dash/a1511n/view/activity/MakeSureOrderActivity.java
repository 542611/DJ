package com.dash.a1511n.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dash.a1511n.R;
import com.dash.a1511n.model.bean.CartBean;
import com.dash.a1511n.model.bean.CreateOrderBean;
import com.dash.a1511n.presenter.CreateOrderPresenter;
import com.dash.a1511n.util.ApiUtil;
import com.dash.a1511n.util.CommonUtils;
import com.dash.a1511n.util.OkHttp3Util_03;
import com.dash.a1511n.view.Iview.CreateOrderInter;
import com.dash.a1511n.view.adapter.SureOrderAdapter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MakeSureOrderActivity extends AppCompatActivity implements View.OnClickListener,CreateOrderInter {

    private ArrayList<CartBean.DataBean.ListBean> list_selected;
    private ImageView detail_image_back;
    private RecyclerView product_list_recycler;
    private TextView text_shi_fu_kuan;
    private TextView text_submit_order;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private double price;
    private CreateOrderPresenter createOrderPresenter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_sure_order);

        //找到控件
        detail_image_back = findViewById(R.id.detail_image_back);
        product_list_recycler = findViewById(R.id.product_list_recycler);
        text_shi_fu_kuan = findViewById(R.id.text_shi_fu_ku);
        text_submit_order = findViewById(R.id.text_submit_order);


        //获取选中的购物车数据的集合
        list_selected = (ArrayList<CartBean.DataBean.ListBean>) getIntent().getSerializableExtra("list_selected");

        //
        initData();

    }

    private void initData() {
        createOrderPresenter = new CreateOrderPresenter(this);


        //设置点击事件
        detail_image_back.setOnClickListener(this);
        text_submit_order.setOnClickListener(this);

        //布局管理器
        product_list_recycler.setLayoutManager(new LinearLayoutManager(MakeSureOrderActivity.this));

        //添加分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MakeSureOrderActivity.this, LinearLayout.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_decoration_shape));

        product_list_recycler.addItemDecoration(dividerItemDecoration);
        //设置适配器
        SureOrderAdapter sureOrderAdapter = new SureOrderAdapter(MakeSureOrderActivity.this, list_selected);
        product_list_recycler.setAdapter(sureOrderAdapter);

        price = 0;
        //显示实付款...计算价格
        for (int i = 0;i<list_selected.size(); i++) {
            price += list_selected.get(i).getBargainPrice() * list_selected.get(i).getNum();
        }
        //格式化两位
        String priceString = decimalFormat.format(price);
        text_shi_fu_kuan.setText("实付款:¥"+priceString);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_image_back://返回

                finish();

                break;
            case R.id.text_submit_order://提交订单...生成订单

                //实际上点击提交订单的操作是:1.生成这个订单2.调用支付宝/微信/网银进行付款(付款的操作在后边学)

                //https://www.zhaoapi.cn/product/createOrder?uid=71&price=99.99

                createOrderPresenter.createOrder(ApiUtil.CREATE_ORDER_URL, CommonUtils.getString("uid"),price);
                break;
        }
    }

    @Override
    public void onCreateOrderSuccess(CreateOrderBean createOrderBean) {

        if ("0".equals(createOrderBean.getCode())) {//创建订单成功...成功之后才能去付款
            //无论付款成功/失败/取消 该订单都已经创建了,,,需要跳转到订单列表,,,并且购物车里面相关商品需要删除

            //1.订单创建成功之后 删除购物车列表中对应的商品...使用递归删除选中的商品
            index = 0;
            deleteProductInCart(list_selected);

        }else {
            Toast.makeText(MakeSureOrderActivity.this,createOrderBean.getMsg(),Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 删除购物车
     * @param list_selected
     */
    private void deleteProductInCart(final ArrayList<CartBean.DataBean.ListBean> list_selected) {

        CartBean.DataBean.ListBean listBean = list_selected.get(index);

        //请求删除购物车的接口...删除成功之后 再次请求查询购物车
        Map<String, String> params = new HashMap<>();
        //?uid=72&pid=1
        params.put("uid",CommonUtils.getString("uid"));
        params.put("pid", String.valueOf(listBean.getPid()));

        OkHttp3Util_03.doPost(ApiUtil.DELETE_CART_URL, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//删除一条成功,,,index++

                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            index ++;//判断是否继续删除.,..如果index<list.size() 继续,,,不是代表全部删完了
                            if (index < list_selected.size()) {
                                //继续
                                deleteProductInCart(list_selected);
                            }else {
                                //删除完成...//1.调支付的操作...//2.跳转到订单列表页面
                                Toast.makeText(MakeSureOrderActivity.this,"应该调用支付的操作,然后再跳转订单列表",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MakeSureOrderActivity.this,OrderListActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}

package com.example.code2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.code2.R;
import com.example.code2.model.bean.CartBean;
import com.example.code2.model.bean.CountPriceBean;
import com.example.code2.model.bean.FenLeiBean;
import com.example.code2.model.bean.HomeBean;
import com.example.code2.presenter.FragmentCartPresenter;
import com.example.code2.presenter.FragmentHomeP;
import com.example.code2.util.ApiUtil;
import com.example.code2.util.CommonUtils;
import com.example.code2.view.Iview.FragmentCartInter;
import com.example.code2.view.Iview.InterFragmentHome;
import com.example.code2.view.Iview.OnItemListner;
import com.example.code2.view.activity.DetailActivity;
import com.example.code2.view.activity.LoginActivity;
import com.example.code2.view.activity.MakeSureOrderActivity;
import com.example.code2.view.adapter.MyExpanableAdapter;
import com.example.code2.view.adapter.TuiJianAdapter;
import com.example.code2.view.custorm.MyExpanableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dash on 2018/1/23.
 */
public class FragmentShoppingCart extends Fragment implements InterFragmentHome,FragmentCartInter, View.OnClickListener {

    private LinearLayout linear_login;
    private Button cart_login;
    private RecyclerView tui_jian_recycler;
    private MyExpanableView my_expanable_view;
    private FragmentHomeP fragmentHomeP;
    private RelativeLayout relative_progress;
    private FragmentCartPresenter fragmentCartPresenter;
    private CheckBox cart_check_all;
    private TextView text_total;
    private TextView text_buy;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;

                //设置显示
                text_total.setText("合计:¥"+countPriceBean.getPriceString());
                text_buy.setText("去结算("+countPriceBean.getCount()+")");
            }
        }
    };
    private MyExpanableAdapter myExpanableAdapter;
    private ImageView empty_cart_image;
    private CartBean cartBean;
    //创建一个集合,,,装的是去结算的时候 选中的所有商品的数据
    private ArrayList<CartBean.DataBean.ListBean> list_selected = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frament_cart_layout,container,false);

        linear_login = view.findViewById(R.id.linear_login);
        cart_login = view.findViewById(R.id.cart_login);
        tui_jian_recycler = view.findViewById(R.id.tui_jian_recycler);
        my_expanable_view = view.findViewById(R.id.my_expanable_view);
        relative_progress = view.findViewById(R.id.relative_progress);
        cart_check_all = view.findViewById(R.id.cart_check_all);
        text_total = view.findViewById(R.id.text_total);
        text_buy = view.findViewById(R.id.text_buy);
        empty_cart_image = view.findViewById(R.id.empty_cart_image);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //去掉默认的指示器
        my_expanable_view.setGroupIndicator(null);

        //失去焦点....界面不是从recyclerView开始显示
        tui_jian_recycler.setFocusable(false);

        //2.为你推荐,,,只需要获取一次
        fragmentHomeP = new FragmentHomeP(this);
        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        fragmentHomeP.getNetData(ApiUtil.HOME_URL);

        fragmentCartPresenter = new FragmentCartPresenter(this);


        //全选 设置点击事件
        cart_check_all.setOnClickListener(this);
        text_buy.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        //Log.e("----","获取到焦点");
        super.onResume();

        initData();

        //为你推荐的数据请求一次....其实为你推荐的数据也是随着登录和不登录改变


    }

    private void initData() {
        //判断是否登录...没有,则显示登录按钮....已经登录显示购物车数据
        if (CommonUtils.getBoolean("isLogin")) {
            //请求购物车的数据...显示购物车
            my_expanable_view.setVisibility(View.VISIBLE);
            linear_login.setVisibility(View.GONE);

            //请求购物车的数据
            getCartData();


        }else {
            //登录按钮的显示
            linear_login.setVisibility(View.VISIBLE);
            my_expanable_view.setVisibility(View.GONE);

            //登录的点击事件
            cart_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

    /**
     * 获取购物车数据
     */
    private void getCartData() {
        //显示进度条
        relative_progress.setVisibility(View.VISIBLE);

        //查询购物车的数据
        fragmentCartPresenter.getCartData(ApiUtil.SELECT_CART,CommonUtils.getString("uid"));

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            //Log.e("----","隐藏");


        }else {
            //Log.e("----","显示");

            initData();

        }

    }

    @Override
    public void onSuccess(final HomeBean homeBean) {
        //瀑布流
        tui_jian_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        //为你推荐设置适配器
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getActivity(), homeBean.getTuijian());
        tui_jian_recycler.setAdapter(tuiJianAdapter);

        //为你推荐的点击事件
        tuiJianAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {

                //跳转的是自己的详情页面
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                //传递pid
                intent.putExtra("pid",homeBean.getTuijian().getList().get(position).getPid());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

    @Override
    public void onFenLeiDataSuccess(FenLeiBean fenLeiBean) {

    }

    @Override
    public void getCartDataNull() {
        relative_progress.setVisibility(View.GONE);
        empty_cart_image.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(),"购物车为空,先去逛逛吧",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCartDataSuccess(final CartBean cartBean) {
        this.cartBean = cartBean;

        //进度条隐藏
        relative_progress.setVisibility(View.GONE);
        empty_cart_image.setVisibility(View.GONE);

        //显示购物车的数据....二级列表设置适配器

        //1.根据某一组里面所有子孩子是否选中,决定当前组是否选中
        for (int i =0;i<cartBean.getData().size();i++) {

            //遍历每一组的数据,,,设置是否选中...有所有的子孩子决定
            CartBean.DataBean dataBean = cartBean.getData().get(i);
            dataBean.setGroupChecked(isAllChildInGroupChecked(dataBean.getList()));
        }

        //2.根据所有的组是否选中,,,决定全选是否选中
        cart_check_all.setChecked(isAllGroupChecked(cartBean));

        //3.
        myExpanableAdapter = new MyExpanableAdapter(getActivity(), cartBean,handler,relative_progress,fragmentCartPresenter);
        my_expanable_view.setAdapter(myExpanableAdapter);

        //展开所有的每一组
        for (int i = 0;i<cartBean.getData().size();i++) {
            my_expanable_view.expandGroup(i);
        }

        //4.计算商品的总价和数量
        myExpanableAdapter.sendPriceAndCount();


        //子条目的点击事件
        my_expanable_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

                //Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

                //跳转的是自己的详情页面
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                //传递pid
                intent.putExtra("pid",cartBean.getData().get(groupPosition).getList().get(childPosition).getPid());
                startActivity(intent);

                return false;
            }
        });
    }

    /**
     * 是否所有的组选中
     * @param cartBean
     * @return
     */
    private boolean isAllGroupChecked(CartBean cartBean) {

        for (int i=0;i<cartBean.getData().size();i++) {
            //组没有选中
            if (! cartBean.getData().get(i).isGroupChecked()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断组中所有的子孩子是否全部选中
     * @param list
     * @return
     */
    private boolean isAllChildInGroupChecked(List<CartBean.DataBean.ListBean> list) {

        for (int i =0;i<list.size();i++) {
            if (list.get(i).getSelected() == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_check_all://全选
                //点击全选的时候,,,,根据全选的状态 改变购物车所有商品的选中状态

                myExpanableAdapter.setAllChildChecked(cart_check_all.isChecked());
                break;
            case R.id.text_buy://去结算
                //当显示的是去结算(0)....打击没有任何反应
                if ("去结算(0)".equals(text_buy.getText().toString())) {
                    return;
                }

                //选中了商品之后,,,跳转到确认订单页面
                Intent intent = new Intent(getActivity(), MakeSureOrderActivity.class);
                //跳转传值...选中的商品的数据...传递一个集合过去...选中的商品的集合

                //先清空一下集合
                list_selected.clear();

                for (int i = 0;i<cartBean.getData().size();i++) {

                    List<CartBean.DataBean.ListBean> list = cartBean.getData().get(i).getList();
                    for (int j = 0;j<list.size();j++) {

                        //判断是否选中,,,如果选中存放到集合中去
                        if (list.get(j).getSelected() == 1) {
                            list_selected.add(list.get(j));
                        }
                    }
                }
                //通过intent把集合传到下一个页面
                intent.putExtra("list_selected",list_selected);

                startActivity(intent);
                break;
        }
    }
}

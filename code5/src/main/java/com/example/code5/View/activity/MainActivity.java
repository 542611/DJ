package com.example.code5.View.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.code5.R;
import com.example.code5.View.adapter.MyAdapter;
import com.example.code5.View.view.IMainView;
import com.example.code5.modle.bean.NewsBean;
import com.example.code5.modle.modle.ModelImpl;
import com.example.code5.presenter.PresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private static final String TAG = "MainActivity----";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDatas() {
        PresenterImpl presenter = new PresenterImpl();
        presenter.showNewsToView(new ModelImpl(), this);
    }

    @Override
    public void showNews(final List<NewsBean.DataBeanX.DataBean> list) {
        Log.d(TAG, "showNews: " + list);

        final MyAdapter myAdapter = new MyAdapter(MainActivity.this, list);

        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnLongItemClick(new MyAdapter.MyLongItemClick() {
            @Override
            public void onItemLongClick(View view, final int postion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除");
                builder.setMessage("确定删除吗?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(postion);
                        myAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }
}

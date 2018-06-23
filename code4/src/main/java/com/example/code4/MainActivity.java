package com.example.code4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerAdapter.OnItemClickListener {

    private static final String TAG =MainActivity.class.getSimpleName() ;
    private Button xian,biao,pubu;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();
    }

    private void initviews() {
        xian = findViewById(R.id.xian);
        biao = findViewById(R.id.biao);
        pubu = findViewById(R.id.pubu);

        xian.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.xian:
                mRecyclerView = (RecyclerView) findViewById(R.id.teach_recycler);
                //1、第一种LinearLayoutManager
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);
              initdata();
                break;
            case R.id.biao:
//2、第二种 GridLayoutManager
                GridLayoutManager layoutManager1 =new GridLayoutManager(this,3);
                mRecyclerView.setLayoutManager(layoutManager1);
              initdata();
                break;
            case R.id.pubu:
                //3、第三种
                StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        //设置布局的排版方向
                 layoutManager2.setOrientation(GridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(layoutManager2);
              initdata();
                break;
        }
    }

    public List<Modle> getData() {
        List<Modle> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Modle model = new Modle();
            model.setName("猴子请来的都比---" + i);
            model.setHeight(((int) (Math.random() * 100 + 200)));
            data.add(model);
        }
        return data;
    }

    @Override
    public void onItemClick(int position, Modle model) {
        Log.e(TAG, "onItemClick: " + position);
    }

    public void initdata(){
        //绑定适配器
        RecyclerAdapter adapter = new RecyclerAdapter(this, getData());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);//将接口传递到数据产生的地方
    }
}

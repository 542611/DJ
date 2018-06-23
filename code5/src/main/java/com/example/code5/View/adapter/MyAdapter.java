package com.example.code5.View.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.code5.R;
import com.example.code5.modle.bean.NewsBean;
import com.example.code5.modle.http.HttpConfig;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter----";
    private final int NEWS1 = 1;
    private final int NEWS2 = 2;
    private final Context context;
    private final List<NewsBean.DataBeanX.DataBean> list;
    private MyLongItemClick myLongItemClick;

    public MyAdapter(Context context, List<NewsBean.DataBeanX.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myLongItemClick.onItemLongClick(v,position);
                return true;
            }
        });

        //开始使用
        int type = getItemViewType(position);
        //根据类型判断
        switch (type) {
            case NEWS1:
                //移仓上面，显示下面
                LinearLayout shang = holder.getShang();
                LinearLayout xia = holder.getXia();
                xia.setVisibility(View.VISIBLE);
                shang.setVisibility(View.GONE);
                //给下面赋值
                holder.getXia_title().setText(list.get(position).getTitle());
                holder.getXia_title1().setText(list.get(position).getTitle());

                List<String> pics = list.get(position).getPics();

                Glide.with(context).load(HttpConfig.pic_url+pics.get(0)).into(holder.getXia_pic1());
                Glide.with(context).load(HttpConfig.pic_url+pics.get(1)).into(holder.getXia_pic2());
                Glide.with(context).load(HttpConfig.pic_url+pics.get(2)).into(holder.getXia_pic3());

                holder.getXia_pic1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "点击----");
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.getXia_pic1(), "alpha", 1, 0, 1);
                        alpha.setDuration(2000);
                        alpha.start();
                    }
                });

                holder.getXia_pic2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "点击----");
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.getXia_pic2(), "alpha", 1, 0, 1);
                        alpha.setDuration(2000);
                        alpha.start();
                    }
                });

                holder.getXia_pic3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "点击----");
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.getXia_pic3(), "alpha", 1, 0, 1);
                        alpha.setDuration(2000);
                        alpha.start();
                    }
                });

                holder.getXia_pinglun().setText(list.get(position).getComment_amount()+"评论");
                holder.getXia_zan().setText(list.get(position).getViews()+"赞");

                break;
            case NEWS2:
                //显示上面，隐藏下面
                LinearLayout shang1 = holder.getShang();
                LinearLayout xia2 = holder.getXia();
                xia2.setVisibility(View.GONE);
                shang1.setVisibility(View.VISIBLE);
                Log.d(TAG, "onBindViewHolder: "+holder.getTitle()+"--"+list);
                Log.d(TAG, "onBindViewHolder: "+holder.getTitle()+"--"+list+"--"+list.get(position));
                Log.d(TAG, "onBindViewHolder: "+holder.getTitle()+"--"+list+"--"+list.get(position)+"--"+list.get(position).getTitle());
                //赋值
                holder.getTitle().setText(list.get(position).getTitle());
                holder.getTitle1().setText(list.get(position).getTitle());

                List<String> pics1 = list.get(position).getPics();
                Log.d(TAG, "图片---: "+holder.getPic());
                Glide.with(context).load(HttpConfig.pic_url+pics1.get(0)).into(holder.getPic());

                holder.getPinglun().setText(list.get(position).getComment_amount()+"评论");
                holder.getZan().setText(list.get(position).getViews()+"赞");

                holder.getPic().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "点击----");
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.getPic(), "alpha", 1, 0, 1);
                        alpha.setDuration(2000);
                        alpha.start();
                    }
                });

                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        NewsBean.DataBeanX.DataBean dataBean = list.get(position);
        int pic_amount = dataBean.getPic_amount();
        if (pic_amount>=3){
            return NEWS1;
        }else {
            return NEWS2;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, title1, xia_title, xia_title1, zan, xia_zan, pinglun, xia_pinglun;
        private ImageView pic, xia_pic1, xia_pic2, xia_pic3;
        private LinearLayout shang,xia;
        public MyViewHolder(View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.pic);
            xia_pic1=itemView.findViewById(R.id.xia_pic1);
            xia_pic2=itemView.findViewById(R.id.xia_pic2);
            xia_pic3=itemView.findViewById(R.id.xia_pic3);
            title = itemView.findViewById(R.id.title);
            title1 = itemView.findViewById(R.id.title1);
            xia_title = itemView.findViewById(R.id.xia_title);
            xia_title1 = itemView.findViewById(R.id.xia_title2);
            zan = itemView.findViewById(R.id.zan);
            xia_zan = itemView.findViewById(R.id.xia_zan);
            pinglun = itemView.findViewById(R.id.pinglun);
            xia_pinglun = itemView.findViewById(R.id.xian_pinglun);
            //上下的布局
            shang=itemView.findViewById(R.id.shang);
            xia=itemView.findViewById(R.id.xia);
        }


        public MyViewHolder(View itemView, TextView title, TextView title1, TextView xia_title, TextView xia_title1, TextView zan, TextView xia_zan, TextView pinglun, TextView xia_pinglun, ImageView pic, ImageView xia_pic1, ImageView xia_pic2, ImageView xia_pic3, LinearLayout shang, LinearLayout xia) {
            super(itemView);
            this.title = title;
            this.title1 = title1;
            this.xia_title = xia_title;
            this.xia_title1 = xia_title1;
            this.zan = zan;
            this.xia_zan = xia_zan;
            this.pinglun = pinglun;
            this.xia_pinglun = xia_pinglun;
            this.pic = pic;
            this.xia_pic1 = xia_pic1;
            this.xia_pic2 = xia_pic2;
            this.xia_pic3 = xia_pic3;
            this.shang = shang;
            this.xia = xia;
        }

        public TextView getTitle() {
            return title;
        }

        public LinearLayout getShang() {
            return shang;
        }

        public void setShang(LinearLayout shang) {
            this.shang = shang;
        }

        public LinearLayout getXia() {
            return xia;
        }

        public void setXia(LinearLayout xia) {
            this.xia = xia;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getTitle1() {
            return title1;
        }

        public void setTitle1(TextView title1) {
            this.title1 = title1;
        }

        public TextView getXia_title() {
            return xia_title;
        }

        public void setXia_title(TextView xia_title) {
            this.xia_title = xia_title;
        }

        public TextView getXia_title1() {
            return xia_title1;
        }

        public void setXia_title1(TextView xia_title1) {
            this.xia_title1 = xia_title1;
        }

        public TextView getZan() {
            return zan;
        }

        public void setZan(TextView zan) {
            this.zan = zan;
        }

        public TextView getXia_zan() {
            return xia_zan;
        }

        public void setXia_zan(TextView xia_zan) {
            this.xia_zan = xia_zan;
        }

        public TextView getPinglun() {
            return pinglun;
        }

        public void setPinglun(TextView pinglun) {
            this.pinglun = pinglun;
        }

        public TextView getXia_pinglun() {
            return xia_pinglun;
        }

        public void setXia_pinglun(TextView xia_pinglun) {
            this.xia_pinglun = xia_pinglun;
        }

        public ImageView getPic() {
            return pic;
        }

        public void setPic(ImageView pic) {
            this.pic = pic;
        }

        public ImageView getXia_pic1() {
            return xia_pic1;
        }

        public void setXia_pic1(ImageView xia_pic1) {
            this.xia_pic1 = xia_pic1;
        }

        public ImageView getXia_pic2() {
            return xia_pic2;
        }

        public void setXia_pic2(ImageView xia_pic2) {
            this.xia_pic2 = xia_pic2;
        }

        public ImageView getXia_pic3() {
            return xia_pic3;
        }

        public void setXia_pic3(ImageView xia_pic3) {
            this.xia_pic3 = xia_pic3;
        }
    }

    public interface MyLongItemClick{
        void onItemLongClick(View view,int postion);
    }
    public void setOnLongItemClick(MyLongItemClick myLongItemClick){
        this.myLongItemClick = myLongItemClick;
    }

}


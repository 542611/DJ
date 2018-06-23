package com.example.code2.view.hodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.code2.R;

/**
 * Created by Dash on 2018/1/25.
 */
public class FenLeiRecyclerOutHolder extends RecyclerView.ViewHolder {

    public TextView recycler_out_text;
    public RecyclerView recycler_innner;

    public FenLeiRecyclerOutHolder(View itemView) {
        super(itemView);

        recycler_out_text = itemView.findViewById(R.id.recycler_out_text);
        recycler_innner = itemView.findViewById(R.id.recycler_innner);

    }
}

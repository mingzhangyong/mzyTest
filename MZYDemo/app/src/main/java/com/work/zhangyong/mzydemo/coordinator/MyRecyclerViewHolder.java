package com.work.zhangyong.mzydemo.coordinator;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangyong on 2016/7/29.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private RecyclerItemClickListener clickListener;
    public MyRecyclerViewHolder(View itemView ,final RecyclerItemClickListener listener) {
        super(itemView);
        this.clickListener = listener;
        itemView.setOnClickListener(this);
    }
    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(clickListener != null){
            clickListener.onItemClick(v,this.getAdapterPosition());
        }
    }

    public interface RecyclerItemClickListener{
        void onItemClick(View view, int position);
    }
}

package com.mralonso.android.presentation.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class FooterViewHolder extends RecyclerView.ViewHolder{

    public FooterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindFooter() {
    }
}

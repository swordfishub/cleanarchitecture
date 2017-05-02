package com.mralonso.android.presentation.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mralonso.android.presentation.listeners.LoadMoreItemsListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class EndlessRecyclerView<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    protected List<T> mData;
    private LoadMoreItemsListener mLoadMoreItemsListener;
    private boolean mHasFooter = false;

    public EndlessRecyclerView() {
        mData = new ArrayList<>();
    }

    public void setLoadMoreItemsListener(LoadMoreItemsListener listener) {
        mLoadMoreItemsListener = listener;
    }

    public void addItems(Collection<T> newData, boolean hasMorePages) {
        if (newData == null) newData = new ArrayList<>();
        mData.addAll(newData);
        mHasFooter = hasMorePages;
        notifyDataSetChanged();
    }

    public void clean() {
        mData.clear();
        mHasFooter = false;
        notifyDataSetChanged();
    }

    public void setNoMorePages() {
        mHasFooter = false;
        notifyDataSetChanged();
    }

    //region Get View

    protected abstract void bindFooter(RecyclerView.ViewHolder holder);

    protected abstract void bindItem(RecyclerView.ViewHolder holder, T t);

    protected abstract RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent);

    protected abstract RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent);

    //endregion

    //region RecyclerView.Adapter<RecyclerView.ViewHolder>

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            bindItem(holder, mData.get(position));
            return;
        } else if (holder.getItemViewType() == TYPE_FOOTER) {
            bindFooter(holder);
            mLoadMoreItemsListener.loadMoreItems();
            return;
        }
        throw new RuntimeException("there is no type that matches the type " + holder.getItemViewType() + " + make sure your using types correctly");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ITEM) {
            return getItemView(inflater, parent);
        } else if (viewType == TYPE_FOOTER) {
            return getFooterView(inflater, parent);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public int getItemCount() {
        int itemCount = mData.size();
        if (mHasFooter) {
            itemCount++;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHasFooter && isPositionFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    //endregion RecyclerView.Adapter<RecyclerView.ViewHolder>

    //region private methods

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    //endregion private methods

}
package com.mralonso.android.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.holders.BookRecyclerViewHolder;
import com.mralonso.android.presentation.listeners.BookClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Book> mData;
    private BookClickListener mBookClickListener;

    //region constructor

    public BooksRecyclerAdapter(BookClickListener propertyClickListener) {
        super();
        mBookClickListener = propertyClickListener;
        mData = new ArrayList<>();
    }

    //region constructor

    //region public methods

    public void addItems(Collection<Book> newData) {
        if (newData == null) return;
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    //endregion public methods

    //region RecyclerView.Adapter<RecyclerView.ViewHolder>

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BookRecyclerViewHolder(inflater.inflate(R.layout.book_card_layout, parent, false), mBookClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookRecyclerViewHolder itemViewHolder = (BookRecyclerViewHolder) holder;
        itemViewHolder.bindBook(mData.get(position));
        return;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //endregion RecyclerView.Adapter<RecyclerView.ViewHolder>

}

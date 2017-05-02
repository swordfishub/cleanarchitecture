package com.mralonso.android.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.holders.BookRecyclerViewHolder;
import com.mralonso.android.presentation.holders.FooterViewHolder;
import com.mralonso.android.presentation.listeners.BookClickListener;
import com.mralonso.android.presentation.views.EndlessRecyclerView;

public class BooksRecyclerAdapter extends EndlessRecyclerView<Book> {

    BookClickListener mBookClickListener;

    public BooksRecyclerAdapter(BookClickListener propertyClickListener) {
        super();
        mBookClickListener = propertyClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent) {
        return new BookRecyclerViewHolder(inflater.inflate(R.layout.book_card_layout, parent, false), mBookClickListener);
    }

    @Override
    protected RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        return new FooterViewHolder(inflater.inflate(R.layout.loading_more_footer, parent, false));
    }

    @Override
    protected void bindItem(RecyclerView.ViewHolder holder, Book book) {
        BookRecyclerViewHolder itemViewHolder = (BookRecyclerViewHolder) holder;
        itemViewHolder.bindBook(book);
    }

    @Override
    protected void bindFooter(RecyclerView.ViewHolder holder) {
        FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        footerViewHolder.bindFooter();
    }
}

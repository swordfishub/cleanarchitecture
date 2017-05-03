package com.mralonso.android.presentation.screens.books;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.adapters.BooksRecyclerAdapter;
import com.mralonso.android.presentation.listeners.BookClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by miguel.rodriguez on 3/5/17.
 */

public class BooksActivityView extends LinearLayout implements BooksActivityViewInterface, BookClickListener {

    private BooksPresenter mPresenter;
    private BooksRecyclerAdapter mAdapter;
    private BooksActivityInterface mActivity;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.loading)
    ProgressBar mLoading;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.error)
    LinearLayout mError;

    @OnClick(R.id.retry)
    public void onRetryClick(){
        if(mPresenter!=null) {
            mPresenter.retry();
        }
    }

    //region constructors

    public BooksActivityView(Context context) {
        super(context);
        createView(context);
    }

    public BooksActivityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createView(context);
    }

    public BooksActivityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BooksActivityView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        createView(context);
    }

    //endregion constructors

    //region private methods

    private void createView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.books_activity_view, this, true);
        ButterKnife.bind(this);
        setListView();
    }

    private void setListView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new BooksRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    //endregion private methods

    //public methods

    public void init(BooksActivityInterface booksActivityInterface, BooksPresenter booksPresenter){
        mActivity = booksActivityInterface;
        mPresenter = booksPresenter;

        if(mActivity!=null){
            mActivity.setToolbar(mToolbar);
        }
    }

    //endregion public methods

    //region BookClickListener

    @Override
    public void onBookSelected(Book book) {
        if(mPresenter!=null){
            mPresenter.bookSelected(book);
        }
    }

    //endregion BookClickListener

    //region BooksActivityViewInterface

    @Override
    public void close() {
        if(mActivity!=null) {
            mActivity.close();
        }
    }

    @Override
    public void showBookDetail(Book book) {
        if(mActivity!=null) {
            mActivity.openBookDetail(book);
        }
    }

    @Override
    public void showBooks(ArrayList<Book> books) {
        mAdapter.addItems(books);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        mError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mError.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoading.setVisibility(View.GONE);
    }

    //endregion BooksActivityViewInterface
}

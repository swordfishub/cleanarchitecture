package com.mralonso.android.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.adapters.BooksRecyclerAdapter;
import com.mralonso.android.presentation.execution.SingletonMainThread;
import com.mralonso.android.presentation.execution.ThreadExecutor;
import com.mralonso.android.presentation.listeners.BookClickListener;
import com.mralonso.android.presentation.listeners.LoadMoreItemsListener;
import com.mralonso.android.presentation.presenters.BooksPresenter;
import com.mralonso.android.presentation.viewInterfaces.BooksView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BooksActivity extends PortraitBaseActivity implements BooksView, BookClickListener, LoadMoreItemsListener {

    private BooksPresenter mPresenter;
    private BooksRecyclerAdapter mBooksAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.loading)
    ProgressBar mLoading;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);
        ButterKnife.bind(this);
        setHomeActionBarToolbar(mToolbar);

        mPresenter = new BooksPresenter(ThreadExecutor.getInstance(), SingletonMainThread.getInstance());
        mPresenter.setNetworkManager(new DeviceNetworkManager(this));
        mPresenter.setDefaultView(this);

        setListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter!=null){
            mPresenter.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPresenter!=null){
            mPresenter.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mPresenter!=null){
            mPresenter.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.destroy();
        }
    }

    //endregion AppCompatActivity

    //region BaseActivity

    @Override
    public void back() {
        if(mPresenter!=null){
            mPresenter.onBackPressed();
        }
    }

    //endregion BaseActivity

    //region BooksView

    @Override
    public void close() {
        getNavigator().finishActivity(this);
    }

    @Override
    public void showBooks(ArrayList<Book> books) {
        mLoading.setVisibility(View.GONE);
        mBooksAdapter.addItems(books);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    //endregion BooksView

    //region BookClickListener

    @Override
    public void onBookSelected(Book book) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("BOOK_TITLE_EXTRA", book.getTitle());
        intent.putExtra("BOOK_ID_EXTRA", book.getId());
        startActivity(intent);
    }

    //endregion BookClickListener

    //region LoadMoreItemsListener

    @Override
    public void loadMoreItems() {
        mPresenter.loadMoreItems();
    }

    //endregion LoadMoreItemsListener

    //region private methods

    private void setListView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBooksAdapter = new BooksRecyclerAdapter(this);
        mRecyclerView.setAdapter(mBooksAdapter);
    }

    //endregion private methods

}

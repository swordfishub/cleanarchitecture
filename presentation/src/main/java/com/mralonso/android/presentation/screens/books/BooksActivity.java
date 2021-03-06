package com.mralonso.android.presentation.screens.books;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mralonso.android.data.repositories.DataRepositoryFactory;
import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BooksUseCase;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.activities.PortraitBaseActivity;
import com.mralonso.android.presentation.adapters.BooksRecyclerAdapter;
import com.mralonso.android.presentation.execution.SingletonMainThread;
import com.mralonso.android.presentation.execution.ThreadExecutor;
import com.mralonso.android.presentation.listeners.BookClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksActivity extends PortraitBaseActivity implements BooksView, BookClickListener {

    private BooksPresenter mPresenter;
    private BooksRecyclerAdapter mAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.loading)
    ProgressBar mLoading;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.error)
    LinearLayout mError;

    @Bind(R.id.retry)
    Button mRetry;

    @Bind(R.id.error_message)
    TextView mErrorMessage;

    @OnClick(R.id.retry)
    public void onRetryClick(){
        mPresenter.retry();
    }

    //region BookClickListener

    @Override
    public void onBookSelected(Book book) {
        mPresenter.bookSelected(book);
    }

    //endregion BookClickListener

    //region BooksView

    @Override
    public void close() {
        getNavigator().finishActivity(this);
    }

    @Override
    public void showBookDetail(Book book) {
        getNavigator().openBookDetail(this, book);
    }

    @Override
    public void showBooks(ArrayList<Book> books) {
        mAdapter.addItems(books);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(boolean show) {
        mError.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        mLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setEmptyErrorText() {
        mErrorMessage.setText(R.string.error_empty_books);
    }

    @Override
    public void setConnectionErrorText() {
        mErrorMessage.setText(R.string.error_loading_books);
    }

    @Override
    public void showRetryButton(boolean show) {
        mRetry.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    //endregion BooksView

    //region BaseActivity

    @Override
    protected void back() {
        mPresenter.back();
    }

    //endregion BaseActivity

    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        ButterKnife.bind(this);

        setListView();
        setHomeActionBarToolbar(mToolbar);

        BooksRepository booksRepository = new DataRepositoryFactory().getDataDefaultRepository(new DeviceNetworkManager(this));
        BooksUseCase booksUseCase = new BooksUseCase(ThreadExecutor.getInstance(), SingletonMainThread.getInstance());
        booksUseCase.setRepository(booksRepository);
        mPresenter = new BooksPresenter(booksUseCase, this);
        mPresenter.startPresenting();
    }

    //endregion AppCompatActivity

    //region private methods

    private void setListView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new BooksRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    //endregion private methods

}

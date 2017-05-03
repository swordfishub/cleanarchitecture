package com.mralonso.android.presentation.screens.books;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.activities.PortraitBaseActivity;
import com.mralonso.android.presentation.execution.SingletonMainThread;
import com.mralonso.android.presentation.execution.ThreadExecutor;

public class BooksActivity extends PortraitBaseActivity implements BooksActivityInterface {

    private BooksPresenter mPresenter;

    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        BooksActivityView booksActivityView = (BooksActivityView) findViewById(R.id.books_activity_view);

        mPresenter = new BooksPresenter(ThreadExecutor.getInstance(), SingletonMainThread.getInstance(),
                new DeviceNetworkManager(this), booksActivityView);

        booksActivityView.init(this, mPresenter);

        mPresenter.startPresenting();
    }

    //endregion AppCompatActivity

    //region BaseActivity

    @Override
    protected void back() {
        mPresenter.back();
    }

    //endregion BaseActivity

    //region BooksActivityInterface

    @Override
    public void setToolbar(Toolbar toolbar) {
        setHomeActionBarToolbar(toolbar);
    }

    @Override
    public void close() {
        getNavigator().finishActivity(this);
    }

    @Override
    public void openBookDetail(Book book) {
        getNavigator().openBookDetail(this, book);
    }

    //endregion BooksActivityInterface

}

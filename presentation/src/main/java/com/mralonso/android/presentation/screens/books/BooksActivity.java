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

        if(mPresenter!=null){
            mPresenter.create();
        }
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
    protected void back() {
        if(mPresenter!=null){
            mPresenter.back();
        }
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

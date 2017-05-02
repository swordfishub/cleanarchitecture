package com.mralonso.android.presentation.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.execution.SingletonMainThread;
import com.mralonso.android.presentation.execution.ThreadExecutor;
import com.mralonso.android.presentation.presenters.BookDetailPresenter;
import com.mralonso.android.presentation.viewInterfaces.BookDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailActivity extends PortraitBaseActivity implements BookDetailView {

    private BookDetailPresenter mPresenter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.loading_layout)
    LinearLayout mLoading;

    @Bind(R.id.info_layout)
    LinearLayout mInfoLayout;

    @Bind(R.id.publish_date)
    TextView mPublishDate;

    @Bind(R.id.description)
    TextView mDescription;

    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_activity);
        ButterKnife.bind(this);
        setHomeActionBarToolbar(mToolbar);

        String bookTitle = getIntent().getStringExtra("BOOK_TITLE_EXTRA");
        String bookId = getIntent().getStringExtra("BOOK_ID_EXTRA");
        getSupportActionBar().setTitle(bookTitle);

        mPresenter = new BookDetailPresenter(ThreadExecutor.getInstance(), SingletonMainThread.getInstance());
        mPresenter.setNetworkManager(new DeviceNetworkManager(this));
        mPresenter.setDefaultView(this);
        mPresenter.setBookId(bookId);
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
    public void showBookDetails(BookDetails bookDetails) {
        mLoading.setVisibility(View.GONE);
        mInfoLayout.setVisibility(View.VISIBLE);
        mPublishDate.setText(bookDetails.getPublishedDate());
        mDescription.setText(Html.fromHtml(bookDetails.getDescription()));
    }


    //endregion BooksView
}

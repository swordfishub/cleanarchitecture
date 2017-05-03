package com.mralonso.android.presentation.screens.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.activities.PortraitBaseActivity;
import com.mralonso.android.presentation.execution.SingletonMainThread;
import com.mralonso.android.presentation.execution.ThreadExecutor;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailActivity extends PortraitBaseActivity implements BookDetailViewInteface {

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
        mPresenter.startPresenting();
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

    //region BooksActivityViewInterface

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


    //endregion BooksActivityViewInterface
}
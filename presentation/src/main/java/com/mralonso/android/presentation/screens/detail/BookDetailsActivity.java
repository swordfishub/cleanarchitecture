package com.mralonso.android.presentation.screens.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mralonso.android.data.repositories.DataRepositoryFactory;
import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BookDetailsUseCase;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.activities.PortraitBaseActivity;
import com.mralonso.android.presentation.execution.SingletonMainThread;
import com.mralonso.android.presentation.execution.ThreadExecutor;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailsActivity extends PortraitBaseActivity implements BookDetailsView {

    private BookDetailsPresenter mPresenter;

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

    @Bind(R.id.error)
    LinearLayout mError;

    @OnClick(R.id.retry)
    public void onRetryClick(){
        mPresenter.retry();
    }

    //region BookDetailsView

    @Override
    public void close() {
        getNavigator().finishActivity(this);
    }

    @Override
    public void showBookDetails(BookDetails bookDetails) {
        mInfoLayout.setVisibility(View.VISIBLE);
        mPublishDate.setText(bookDetails.getPublishedDate());
        mDescription.setText(Html.fromHtml(bookDetails.getDescription()));
    }

    @Override
    public void showError(boolean show) {
        mError.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        mLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    //endregion BookDetailsView

    //region BaseActivity

    @Override
    public void back() {
        mPresenter.back();
    }

    //endregion BaseActivity

    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_activity);

        ButterKnife.bind(this);

        setHomeActionBarToolbar(mToolbar);

        String bookTitle = getNavigator().obtainBookTitleExtra(getIntent());
        String bookId = getNavigator().obtainBookIdExtra(getIntent());
        getSupportActionBar().setTitle(bookTitle);

        BooksRepository repository = new DataRepositoryFactory().getDataDefaultRepository(new DeviceNetworkManager(this));
        BookDetailsUseCase bookDetailsUseCase = new BookDetailsUseCase(ThreadExecutor.getInstance(), SingletonMainThread.getInstance());
        bookDetailsUseCase.setRepository(repository);
        bookDetailsUseCase.setId(bookId);
        mPresenter = new BookDetailsPresenter(bookDetailsUseCase, this);
        mPresenter.startPresenting();
    }

    //endregion AppCompatActivity
}

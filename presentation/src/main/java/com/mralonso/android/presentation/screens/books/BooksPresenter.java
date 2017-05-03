package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.data.repositories.DataRepositoryFactory;
import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.callbacks.BooksCallback;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BooksUseCase;
import com.mralonso.android.presentation.presenters.AbstractPresenter;

import java.util.ArrayList;

public class BooksPresenter extends AbstractPresenter implements BooksCallback {

    BooksActivityViewInterface mBooksActivityView;
    DeviceNetworkManager mDeviceNetworkManager;
    BooksUseCase mBooksUseCase;

    //region constructor

    public BooksPresenter(Executor executor, MainThread mainThread,
                          DeviceNetworkManager networkManager,
                          BooksActivityViewInterface booksActivityViewInterface) {

        super(executor, mainThread);

        mDeviceNetworkManager = networkManager;
        mBooksActivityView = booksActivityViewInterface;
    }

    //endregion constructor

    //region AbstractPresenter

    @Override
    public void startPresenting() {
        super.startPresenting();

        if(mBooksActivityView!=null){
            mBooksActivityView.showLoading();
        }
        mBooksUseCase = new BooksUseCase(mExecutor, mMainThread);
        BooksRepository repository = new DataRepositoryFactory().getDataDefaultRepository(mDeviceNetworkManager);
        mBooksUseCase.setRepository(repository);
        mBooksUseCase.setCallback(this);
        mBooksUseCase.execute();
    }


    //endregion AbstractPresenter

    //region public methods

    public void back() {
        if(mBooksActivityView !=null) {
            mBooksActivityView.close();
        }
    }

    public void retry() {
        if(mBooksActivityView !=null) {
            mBooksActivityView.hideError();
            mBooksActivityView.showLoading();
        }
        if(mBooksUseCase!=null) {
            mBooksUseCase.execute();
        }
    }

    public void bookSelected(Book book) {
        if(mBooksActivityView !=null) {
            mBooksActivityView.showBookDetail(book);
        }
    }

    //endregion public methods

    //region BooksCallback

    @Override
    public void onBooksReceived(ArrayList<Book> books) {
        if(mBooksActivityView !=null) {
            mBooksActivityView.hideLoading();
            mBooksActivityView.showBooks(books);
        }
    }

    @Override
    public void onBooksNotReceived() {
        if(mBooksActivityView !=null) {
            mBooksActivityView.hideLoading();
            mBooksActivityView.showError();
        }
    }

    //endregion BooksCallback
}

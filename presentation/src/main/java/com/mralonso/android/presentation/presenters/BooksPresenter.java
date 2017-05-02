package com.mralonso.android.presentation.presenters;

import com.mralonso.android.data.repositories.DataRepositoryFactory;
import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.callbacks.BooksCallback;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BooksUseCase;
import com.mralonso.android.presentation.viewInterfaces.BooksView;

import java.util.ArrayList;

public class BooksPresenter extends AbstractPresenter implements BasePresenter, BooksCallback {

    BooksView mBooksView;
    DeviceNetworkManager mDeviceNetworkManager;

    public BooksPresenter(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    public void setDefaultView(BooksView booksView){
        mBooksView = booksView;
    }

    public void onBackPressed(){
        if(mBooksView !=null) {
            mBooksView.close();
        }
    }

    public void setNetworkManager(DeviceNetworkManager deviceNetworkManager){
        mDeviceNetworkManager = deviceNetworkManager;
    }

    public void loadMoreItems(){

    }

    //region BasePresenter

    @Override
    public void resume() {
        BooksUseCase booksUseCase = new BooksUseCase(mExecutor, mMainThread);
        BooksRepository repository = new DataRepositoryFactory().getDataDefaultRepository(mDeviceNetworkManager);
        booksUseCase.setRepository(repository);
        booksUseCase.setCallback(this);
        booksUseCase.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    //endregion BasePresenter

    //region BooksCallback

    @Override
    public void onBooksReceived(ArrayList<Book> books) {
        mBooksView.showBooks(books);
    }

    @Override
    public void onBooksNotReceived() {
        //TODO: show error
    }

    //endregion BooksCallback
}

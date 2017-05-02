package com.mralonso.android.presentation.presenters;

import com.mralonso.android.data.repositories.DataRepositoryFactory;
import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.callbacks.BookDetailCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BookDetailUseCase;
import com.mralonso.android.presentation.viewInterfaces.BookDetailView;

public class BookDetailPresenter extends AbstractPresenter implements BasePresenter, BookDetailCallback {

    BookDetailView mBookDetailView;
    DeviceNetworkManager mDeviceNetworkManager;
    String mBookId;

    public BookDetailPresenter(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    public void setDefaultView(BookDetailView booksView){
        mBookDetailView = booksView;
    }

    public void setBookId(String bookId){
        mBookId = bookId;
    }

    public void onBackPressed(){
        if(mBookDetailView !=null) {
            mBookDetailView.close();
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
        BookDetailUseCase bookDetailUseCase = new BookDetailUseCase(mExecutor, mMainThread);
        bookDetailUseCase.setId(mBookId);
        BooksRepository repository = new DataRepositoryFactory().getDataDefaultRepository(mDeviceNetworkManager);
        bookDetailUseCase.setRepository(repository);
        bookDetailUseCase.setCallback(this);
        bookDetailUseCase.execute();
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
    public void onBookDetailsReceived(BookDetails bookDetails) {
        mBookDetailView.showBookDetails(bookDetails);
    }

    @Override
    public void onBooksDetailsNotReceived() {
        //TODO: show error
    }

    //endregion BooksCallback
}
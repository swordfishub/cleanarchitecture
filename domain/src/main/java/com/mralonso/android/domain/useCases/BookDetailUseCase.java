package com.mralonso.android.domain.useCases;

import com.mralonso.android.domain.callbacks.BookDetailCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;

public class BookDetailUseCase extends AbstractUseCase implements UseCase {

    BooksRepository mRepository;
    BookDetailCallback mCallback;
    String mBookId;

    public BookDetailUseCase(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    public void setRepository(BooksRepository repository){
        mRepository = repository;
    }

    public void setCallback(BookDetailCallback callback){
        mCallback = callback;
    }

    public void setId(String id){
        mBookId = id;
    }

    @Override
    public void run() {

        if(mBookId!=null) {

            BookDetails bookDetails = mRepository.obtainBookDetails(mBookId);

            if (bookDetails != null) {
                postDefaultDataReceived(bookDetails);
            } else {
                postDefaultDataNotReceived();
            }
        }
    }

    private void postDefaultDataReceived(final BookDetails bookDetails){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onBookDetailsReceived(bookDetails);
            }
        });
    }

    private void postDefaultDataNotReceived(){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onBooksDetailsNotReceived();
            }
        });
    }
}
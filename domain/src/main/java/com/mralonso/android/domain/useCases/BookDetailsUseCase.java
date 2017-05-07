package com.mralonso.android.domain.useCases;

import com.mralonso.android.domain.callbacks.BookDetailsCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;

public class BookDetailsUseCase extends AbstractUseCase implements UseCase {

    BooksRepository mRepository;
    BookDetailsCallback mCallback;
    String mBookId;

    public BookDetailsUseCase(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    public void setRepository(BooksRepository repository){
        mRepository = repository;
    }

    public void setCallback(BookDetailsCallback callback){
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
                postDataReceived(bookDetails);
            } else {
                postDataNotReceived();
            }
        }
    }

    public void postDataReceived(final BookDetails bookDetails){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onBookDetailsReceived(bookDetails);
            }
        });
    }

    public void postDataNotReceived(){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onBooksDetailsNotReceived();
            }
        });
    }
}
package com.mralonso.android.domain.useCases;

import com.mralonso.android.domain.callbacks.BooksCallback;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;

import java.util.ArrayList;

public class BooksUseCase extends AbstractUseCase implements UseCase {

    BooksRepository mRepository;
    BooksCallback mCallback;

    public BooksUseCase(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    public void setRepository(BooksRepository repository){
        mRepository = repository;
    }

    public void setCallback(BooksCallback callback){
        mCallback = callback;
    }

    @Override
    public void run() {

        ArrayList<Book> books = mRepository.obtainBooks("code");

        if(books !=null){
            postDefaultDataReceived(books);
        }
        else{
            postDefaultDataNotReceived();
        }
    }

    private void postDefaultDataReceived(final ArrayList<Book> books){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onBooksReceived(books);
            }
        });
    }

    private void postDefaultDataNotReceived(){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onBooksNotReceived();
            }
        });
    }
}
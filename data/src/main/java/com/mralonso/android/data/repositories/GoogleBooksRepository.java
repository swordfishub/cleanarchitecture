package com.mralonso.android.data.repositories;

import com.mralonso.android.data.data.GoogleBookDetail;
import com.mralonso.android.data.data.GoogleBooks;
import com.mralonso.android.data.mapper.GoogleBooksMapper;
import com.mralonso.android.data.providers.GoogleBooksProvider;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.repositories.BooksRepository;

import java.util.ArrayList;

public class GoogleBooksRepository implements BooksRepository {

    private GoogleBooksMapper mMapper;
    private GoogleBooksProvider mProvider;

    public GoogleBooksRepository(GoogleBooksMapper mMapper, GoogleBooksProvider mProvider) {
        this.mMapper = mMapper;
        this.mProvider = mProvider;
    }

    @Override
    public ArrayList<com.mralonso.android.domain.data.Book> obtainBooks(String name) {
        if(mProvider==null && mMapper==null) return null;

        GoogleBooks response = mProvider.obtainBooks(name);
        if(response !=null){
            return mMapper.transform(response);
        }
        else{
            return null;
        }
    }

    @Override
    public BookDetails obtainBookDetails(String bookId) {
        if(mProvider==null && mMapper==null) return null;

        GoogleBookDetail response = mProvider.obtainBookDetails(bookId);
        if(response !=null){
            return mMapper.transform(response);
        }
        else{
            return null;
        }
    }
}

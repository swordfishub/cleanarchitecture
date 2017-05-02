package com.mralonso.android.data.mapper;

import com.mralonso.android.data.data.GoogleBook;
import com.mralonso.android.data.data.GoogleBookDetail;
import com.mralonso.android.data.data.GoogleBooks;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.data.BookDetails;

import java.util.ArrayList;

public class GoogleBooksMapper {

    public ArrayList<Book> transform(GoogleBooks googleBooks){
        if(googleBooks ==null || googleBooks.getBooks()==null) return null;

        ArrayList<Book> domainBooks = new ArrayList<>();
        for(GoogleBook googleBook : googleBooks.getBooks()){
            domainBooks.add(transform(googleBook));
        }

        return domainBooks;
    }

    public BookDetails transform(GoogleBookDetail googleBookDetail){
        if(googleBookDetail==null) return null;

        BookDetails bookDetails = new BookDetails();
        bookDetails.setDescription(googleBookDetail.getDescription());
        bookDetails.setPublishedDate(googleBookDetail.getPublishedDate());

        return bookDetails;

    }

    private Book transform(GoogleBook googleBook){
        if(googleBook ==null) return null;

        Book book = new Book();
        book.setId(googleBook.getId());
        book.setTitle(googleBook.getTitle());
        book.setAuthor(googleBook.getAuthors());
        book.setImage(googleBook.getImageUrl());

        return book;
    }
}

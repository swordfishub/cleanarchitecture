package com.mralonso.android.domain.repositories;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.data.BookDetails;

import java.util.ArrayList;

public interface BooksRepository {

    ArrayList<Book> obtainBooks(String name);

    BookDetails obtainBookDetails(String bookId);
}

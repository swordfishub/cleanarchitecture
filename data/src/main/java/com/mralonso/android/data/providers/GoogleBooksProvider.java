package com.mralonso.android.data.providers;

import com.mralonso.android.data.data.GoogleBookDetail;
import com.mralonso.android.data.data.GoogleBooks;

public interface GoogleBooksProvider {

    GoogleBooks obtainBooks(String name);

    GoogleBookDetail obtainBookDetails(String id);
}

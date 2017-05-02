package com.mralonso.android.data.retrofit;

import com.mralonso.android.data.data.GoogleBookDetail;
import com.mralonso.android.data.data.GoogleBooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleBooksAPIEndpointInterface {

    String GOOGLE_BOOKS_API = "https://www.googleapis.com/";
    
    @GET("books/v1/volumes")
    Call<GoogleBooks> getBooks(@Query("q") String name);

    @GET("books/v1/volumes/{bookId}")
    Call<GoogleBookDetail> getBookDetails(@Path("bookId") String bookId);

}


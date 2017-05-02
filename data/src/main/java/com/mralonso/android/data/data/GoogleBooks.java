package com.mralonso.android.data.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoogleBooks {

    @SerializedName("items")
    ArrayList<GoogleBook> mBooks;

    public ArrayList<GoogleBook> getBooks() {
        return mBooks;
    }

}

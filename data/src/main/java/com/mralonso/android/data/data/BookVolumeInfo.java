package com.mralonso.android.data.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookVolumeInfo {

    @SerializedName("title")
    String mTitle;

    @SerializedName("authors")
    ArrayList<String> mAuthors;

    @SerializedName("imageLinks")
    BookImages mImages;

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<String> getAuthors() {
        return mAuthors;
    }

    public BookImages getImages() {
        return mImages;
    }
}

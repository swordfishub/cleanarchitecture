package com.mralonso.android.domain.data;

import java.util.ArrayList;

public class Book {

    private String mId;
    private String mTitle;
    private ArrayList<String> mAuthors;
    private String mImage;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public ArrayList<String> getAuthors() {
        return mAuthors;
    }

    public void setAuthor(ArrayList<String> mAuthor) {
        this.mAuthors = mAuthor;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }
}

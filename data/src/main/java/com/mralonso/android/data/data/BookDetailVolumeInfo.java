package com.mralonso.android.data.data;

import com.google.gson.annotations.SerializedName;

public class BookDetailVolumeInfo {

    @SerializedName("publishedDate")
    String mPublishedDate;

    @SerializedName("description")
    String mDescription;

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public String getDescription() {
        return mDescription;
    }
}

package com.mralonso.android.data.data;

import com.google.gson.annotations.SerializedName;

public class BookImages {

    @SerializedName("thumbnail")
    String mImageUrl;

    public String getImageUrl() {
        return mImageUrl;
    }
}

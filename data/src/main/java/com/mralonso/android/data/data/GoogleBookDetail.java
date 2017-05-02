package com.mralonso.android.data.data;

import com.google.gson.annotations.SerializedName;

public class GoogleBookDetail {

    @SerializedName("volumeInfo")
    BookDetailVolumeInfo mVolumeInfo;

    public String getPublishedDate(){
        if(mVolumeInfo==null) return null;

        return mVolumeInfo.getPublishedDate();
    }

    public String getDescription(){
        if(mVolumeInfo==null) return null;

        return mVolumeInfo.getDescription();
    }
}

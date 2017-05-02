package com.mralonso.android.data.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoogleBook {

    @SerializedName("id")
    String mId;

    @SerializedName("volumeInfo")
    BookVolumeInfo mVolumeInfo;

    public String getId() {
        return mId;
    }

    public String getTitle(){
        if(mVolumeInfo==null) return null;

        return mVolumeInfo.getTitle();
    }

    public ArrayList<String> getAuthors(){
        if(mVolumeInfo==null) return null;

        return mVolumeInfo.getAuthors();
    }

    public String getImageUrl(){
        if(mVolumeInfo==null && mVolumeInfo.getImages()!=null) return null;

        return mVolumeInfo.getImages().getImageUrl();
    }
}

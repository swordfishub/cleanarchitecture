package com.mralonso.android.presentation.navigation;

import android.app.Activity;

public class Navigator {

    private static Navigator sInstance;

    private Navigator(){
        //empty
    }

    public static Navigator getInstance(){
        if(sInstance ==null){
            sInstance = new Navigator();
        }
        return sInstance;
    }

    public void finishActivity(Activity activity){
        activity.finish();
    }

}

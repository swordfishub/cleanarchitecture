package com.mralonso.android.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DeviceNetworkManager {

    private Context mContext;

    public DeviceNetworkManager(Context context) {
        mContext = context;
    }

    public boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

}

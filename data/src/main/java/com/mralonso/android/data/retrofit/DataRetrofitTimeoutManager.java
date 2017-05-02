package com.mralonso.android.data.retrofit;

public class DataRetrofitTimeoutManager {

    private static final int CONNECT_TIMEOUT_SLOW = 10;
    private static final int CONNECT_TIMEOUT_FAST = 5;
    private static final int READ_TIMEOUT_SLOW = 20;
    private static final int READ_TIMEOUT_FAST = 10;
    private static final int WRITE_TIMEOUT_SLOW = 30;
    private static final int WRITE_TIMEOUT_FAST = 20;

    private static final String MOBILE_2G = "2g";
    private static final String MOBILE_UNDEFINED = "Undefined";

    public int getConnectTimeout(String networkType){

        if(networkType==null) return CONNECT_TIMEOUT_SLOW;

        switch (networkType){
            case MOBILE_2G:
            case MOBILE_UNDEFINED:
                return CONNECT_TIMEOUT_SLOW;
            default:
                return CONNECT_TIMEOUT_FAST;
        }
    }

    public int getReadTimeout(String networkType){

        if(networkType==null) return READ_TIMEOUT_SLOW;

        switch (networkType){
            case MOBILE_2G:
            case MOBILE_UNDEFINED:
                return READ_TIMEOUT_SLOW;
            default:
                return READ_TIMEOUT_FAST;
        }
    }

    public int getWriteTimeout(String networkType){

        if(networkType==null) return WRITE_TIMEOUT_SLOW;

        switch (networkType){
            case MOBILE_2G:
            case MOBILE_UNDEFINED:
                return WRITE_TIMEOUT_SLOW;
            default:
                return WRITE_TIMEOUT_FAST;
        }
    }
}

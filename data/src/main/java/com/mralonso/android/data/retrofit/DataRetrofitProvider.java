package com.mralonso.android.data.retrofit;

import com.mralonso.android.data.utils.DeviceNetworkManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class DataRetrofitProvider {

    protected DeviceNetworkManager mNetworkManager;

    public DataRetrofitProvider(DeviceNetworkManager networkUtils) {
        mNetworkManager = networkUtils;
    }

    private Retrofit getRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    protected boolean hasInternetConnection() {
        return mNetworkManager.isNetworkConnected();
    }

    protected GoogleBooksAPIEndpointInterface createGoogleBooksAPIClient(){

        Retrofit retrofit = getRetrofit(GoogleBooksAPIEndpointInterface.GOOGLE_BOOKS_API);

        return retrofit.create(GoogleBooksAPIEndpointInterface.class);
    }
}

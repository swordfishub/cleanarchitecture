package com.mralonso.android.data.providers;

import com.mralonso.android.data.data.GoogleBookDetail;
import com.mralonso.android.data.data.GoogleBooks;
import com.mralonso.android.data.retrofit.DataRetrofitProvider;
import com.mralonso.android.data.retrofit.GoogleBooksAPIEndpointInterface;
import com.mralonso.android.data.utils.DeviceNetworkManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GoogleBooksRetrofitProvider extends DataRetrofitProvider implements GoogleBooksProvider {

    public GoogleBooksRetrofitProvider(DeviceNetworkManager networkUtils) {
        super(networkUtils);
    }

    //region Books Provider

    @Override
    public GoogleBooks obtainBooks(String name) {

        if(!hasInternetConnection()){
            return null;
        }

        GoogleBooksAPIEndpointInterface apiClient = createGoogleBooksAPIClient();

        Call<GoogleBooks> call = apiClient.getBooks(name);
        try {
            Response<GoogleBooks> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public GoogleBookDetail obtainBookDetails(String id) {

        if(!hasInternetConnection()){
            return null;
        }

        GoogleBooksAPIEndpointInterface apiClient = createGoogleBooksAPIClient();

        Call<GoogleBookDetail> call = apiClient.getBookDetails(id);
        try {
            Response<GoogleBookDetail> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    //endregion Books Provider
}

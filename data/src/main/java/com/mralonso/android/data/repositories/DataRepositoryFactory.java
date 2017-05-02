package com.mralonso.android.data.repositories;

import com.mralonso.android.data.mapper.GoogleBooksMapper;
import com.mralonso.android.data.providers.GoogleBooksRetrofitProvider;
import com.mralonso.android.data.utils.DeviceNetworkManager;

public class DataRepositoryFactory {

    public GoogleBooksRepository getDataDefaultRepository(DeviceNetworkManager deviceNetworkManager){
        return new GoogleBooksRepository(new GoogleBooksMapper(), new GoogleBooksRetrofitProvider(deviceNetworkManager));
    }
}

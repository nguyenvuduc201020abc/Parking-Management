package com.example.vehiclemanagemennt.Data.Network;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HttpClient {
    private Retrofit retrofitInstance;
    private HttpApi httpAPI;

    public HttpClient(String apiEndpoint) {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(apiEndpoint)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            httpAPI = retrofitInstance.create(HttpApi.class);
        }
    }

    public HttpApi getHttpAPI() {
        return httpAPI;
    }
}

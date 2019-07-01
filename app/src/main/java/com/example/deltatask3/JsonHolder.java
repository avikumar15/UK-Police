package com.example.deltatask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonHolder {

    @GET("api/forces")
    Call<List<FetchData>> getDATA();

}

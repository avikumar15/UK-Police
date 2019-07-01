package com.example.deltatask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CrimeListLocationInterface {

    @GET("api/crimes-street/all-crime")
    Call<List<GunahKiList>> getTHEdataLIST(
            @Query("lat")float lat,
            @Query("lng")float lng,
            @Query("date")String date);

}

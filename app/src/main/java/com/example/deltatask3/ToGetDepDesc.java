package com.example.deltatask3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ToGetDepDesc {

    public String var="cambridgeshire";

    @GET("api/forces/{id}")
    Call<DepDesc> getDESC(@Path("id") String IIT);

}

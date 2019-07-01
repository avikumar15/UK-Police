package com.example.deltatask3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ToGetCrimeDetails {

    @GET("{idd}")
    Call<TheParticularCrimeClass> getTheInfo(@Path("idd")String SambharMeinMirchyNITTrichy);

}

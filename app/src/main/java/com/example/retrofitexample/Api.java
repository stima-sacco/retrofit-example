package com.example.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public class Api {
    static String BASE_URL = "https://farisi.africa/api/client-to-business/";

    @POST("api/client-to-business/")
    Call<List<MpesaResponse>> clientToBusiness() {
        return null;
    }
}

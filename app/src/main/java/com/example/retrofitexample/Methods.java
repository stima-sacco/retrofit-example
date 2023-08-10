package com.example.retrofitexample;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.PartMap;

public interface Methods {
    @Headers({"Accept: application/json","Content-Type: application/json; charset=utf-8"})
    @POST("/api/client-to-business/")
    Call<MpesaResponse> clientToBusiness(@Body JsonObject jsonBody);

    @Headers({"Accept: application/json","Content-Type: application/json; charset=utf-8"})
    @POST("/api/transaction-response/")
    Call<TransactionResponse> transactionResponse(@Body JsonObject jsonBody);
}
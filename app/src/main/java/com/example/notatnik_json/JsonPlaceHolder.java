package com.example.notatnik_json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("db.json")
    public Call<List<Waluta>> getWaluta();
}

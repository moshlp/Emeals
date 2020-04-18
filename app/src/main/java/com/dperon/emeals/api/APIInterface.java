package com.dperon.emeals.api;

import com.dperon.emeals.model.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("46168/46168_295947.json")
    Call<Recipe> getRecipe1();

    @GET("37767/37767_241270.json")
    Call<Recipe> getRecipe2();
}

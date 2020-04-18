package com.dperon.emeals.viewmodel;

import android.util.Log;
import android.widget.ProgressBar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dperon.emeals.api.APIClient;
import com.dperon.emeals.api.APIInterface;
import com.dperon.emeals.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityViewmodel extends ViewModel {

    public static final String TAG = "MainActivityViewmodel";
    Retrofit retrofit;
    APIInterface apiInterface;
    public MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
    List<Recipe> list = new ArrayList<>();

    public MainActivityViewmodel(){
        retrofit = APIClient.getClient();
        apiInterface = retrofit.create(APIInterface.class);
        getRecipes();
    }

    private void getRecipes() {
        //recipe 1
        Call<Recipe> call1 = apiInterface.getRecipe1();
        call1.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                    getRecipe2();
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getRecipe2() {
        //recipe 2
        Call<Recipe> call2 = apiInterface.getRecipe2();
        call2.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                    recipes.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }


}

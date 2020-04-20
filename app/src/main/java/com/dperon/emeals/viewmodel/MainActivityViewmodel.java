package com.dperon.emeals.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dperon.emeals.api.APIClient;
import com.dperon.emeals.api.APIInterface;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.model.entities.RecipeEntity;
import com.dperon.emeals.repository.RecipeRepository;
import com.dperon.emeals.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityViewmodel extends AndroidViewModel {

    public static final String TAG = "MainActivityViewmodel";
    Retrofit retrofit;
    APIInterface apiInterface;
    public MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
    List<Recipe> list = new ArrayList<>();
    RecipeRepository repository;

    public MainActivityViewmodel(Application application){
        super(application);
        retrofit = APIClient.getClient();
        apiInterface = retrofit.create(APIInterface.class);
        repository = new RecipeRepository(application);
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
                    storeToDB(list);
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void storeToDB(List<Recipe> list) {
        list.forEach(recipe -> repository.insert(recipeToEntity(recipe)));
    }

    private RecipeEntity recipeToEntity(Recipe recipe) {
        RecipeEntity entity = new RecipeEntity();
        entity.setImage(Utils.ToBase64(recipe.getMain().getImage()));
        //TODO
//        entity.setIngredients(recipe.getMain().getIngredients());
//        entity.setInstructions(recipe.getMain().getInstructions());
        entity.setTitle(recipe.getPlanTitle());
        return entity;
    }


}

package com.dperon.emeals.repository;

import android.app.Application;

import com.dperon.emeals.dao.RecipeDao;
import com.dperon.emeals.db.RecipeDatabase;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.model.entities.RecipeEntity;

public class RecipeRepository {

    private RecipeDao dao;

    public RecipeRepository(Application application){
        RecipeDatabase database = RecipeDatabase.getDatabase(application);
        dao = database.recipeDao();
    }

    public void insert(RecipeEntity recipe) {
        RecipeDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertRecipe(recipe);
        });
    }

}

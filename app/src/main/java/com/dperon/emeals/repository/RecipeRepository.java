package com.dperon.emeals.repository;

import android.app.Application;

import androidx.room.Transaction;

import com.dperon.emeals.dao.RecipeDao;
import com.dperon.emeals.db.RecipeDatabase;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.model.entities.RecipeEntity;
import com.dperon.emeals.utils.Utils;

import java.util.List;

public class RecipeRepository {

    private RecipeDao dao;

    public RecipeRepository(Application application){
        RecipeDatabase database = RecipeDatabase.getDatabase(application);
        dao = database.recipeDao();
    }

    public void insertRecipeEntity(List<RecipeEntity> recipes) {
        RecipeDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertRecipe(recipes);
        });
    }

    public List<Recipe> getAll(){
        return Utils.entityToModel(dao.getAll());
    }

    public void updateTitle(RecipeEntity recipeEntity){
        dao.updateTitle(recipeEntity);
    }

}

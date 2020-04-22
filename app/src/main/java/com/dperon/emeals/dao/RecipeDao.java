package com.dperon.emeals.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.model.entities.RecipeEntity;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    public List<RecipeEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertRecipe(List<RecipeEntity> recipes);

    @Update
    public void updateUsers(RecipeEntity recipe);

}

package com.dperon.emeals.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dperon.emeals.dao.RecipeDao;
import com.dperon.emeals.model.entities.RecipeEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Database(entities = {RecipeEntity.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();
    private static RecipeDatabase INSTANCE;
    private static final String DB_NAME = "recipe_database";
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static RecipeDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized ( (RecipeDatabase.class)){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

}

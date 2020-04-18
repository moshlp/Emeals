package com.dperon.emeals.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dperon.emeals.R;
import com.dperon.emeals.adapters.RecipeRVAdapter;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.viewmodel.MainActivityViewmodel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewmodel viewmodel;
    private RecyclerView recyclerView;
    private RecipeRVAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewmodel = new ViewModelProvider(this).get(MainActivityViewmodel.class);

        recyclerView = findViewById(R.id.recipesRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        viewmodel.recipes.observe(this, recipes -> {
            mAdapter = new RecipeRVAdapter(MainActivity.this, recipes, model -> Log.d("--message", model.getPlanMobileTitle()));
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        });








    }
}

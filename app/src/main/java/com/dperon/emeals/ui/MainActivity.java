package com.dperon.emeals.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dperon.emeals.R;
import com.dperon.emeals.adapters.RecipeRVAdapter;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.utils.CheckNetwork;
import com.dperon.emeals.viewmodel.MainActivityViewmodel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewmodel viewmodel;
    private RecyclerView recyclerView;
    private RecipeRVAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String[] PERMISSIONS = { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Network monitoring service
        CheckNetwork network = new CheckNetwork(getApplicationContext());
        network.registerNetworkCallback();

        verifyPermissions();

        progressBar = findViewById(R.id.pBar);

        viewmodel = new ViewModelProvider(this).get(MainActivityViewmodel.class);

        recyclerView = findViewById(R.id.recipesRV);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecipeRVAdapter(MainActivity.this, new RecipeRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe model) {

            }
        });
        recyclerView.setAdapter(mAdapter);

        viewmodel.recipes.observe(this, recipes -> {
            //TODO cambiar progressbar
            progressBar.setVisibility(View.VISIBLE);
            mAdapter.setRecipes(recipes);
            progressBar.setVisibility(View.INVISIBLE);
        });
    }


    public void verifyPermissions()
    {
        // This will return the current Status
        int permissionExternalMemory = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissionExternalMemory != PackageManager.PERMISSION_GRANTED)
        {
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(MainActivity.this,PERMISSIONS,1);
        }
    }
}

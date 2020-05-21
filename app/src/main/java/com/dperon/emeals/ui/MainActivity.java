package com.dperon.emeals.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dperon.emeals.R;
import com.dperon.emeals.adapters.RecipeRVAdapter;
import com.dperon.emeals.databinding.ActivityMainBinding;
import com.dperon.emeals.model.entities.RecipeEntity;
import com.dperon.emeals.utils.CheckNetwork;
import com.dperon.emeals.utils.Utils;
import com.dperon.emeals.viewmodel.MainActivityViewmodel;

public class MainActivity extends AppCompatActivity {

    MainActivityViewmodel viewmodel;
    private RecipeRVAdapter mAdapter;
    private static final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Network monitoring service
        CheckNetwork network = new CheckNetwork(getApplicationContext());
        network.registerNetworkCallback();

        verifyPermissions();

        viewmodel = new ViewModelProvider(this).get(MainActivityViewmodel.class);
//        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recipesRV.setLayoutManager(layoutManager);
        mAdapter = new RecipeRVAdapter(MainActivity.this, model -> {
            AlertDialog.Builder changeTitleAlert = new AlertDialog.Builder(MainActivity.this);
            changeTitleAlert.setTitle("Change title");
            final View customLayout = getLayoutInflater().inflate(R.layout.dialog_title, null);
            changeTitleAlert.setView(customLayout);
            TextView newtitle = customLayout.findViewById(R.id.newtitle);
            newtitle.setText(model.getMain().getTitle());
            changeTitleAlert.setNeutralButton("OK",
                    (arg0, arg1) -> {
                        if (newtitle.length() != 0) {
                            model.getMain().setTitle(newtitle.getText().toString());
                            RecipeEntity entity = Utils.modelToEntity(model);
                            viewmodel.updateTitle(entity);
                        }
                    });
            AlertDialog dialog = changeTitleAlert.create();
            dialog.show();
        });

        binding.recipesRV.setAdapter(mAdapter);

        viewmodel.recipes.observe(this, recipes -> {
            binding.pBar.setVisibility(View.VISIBLE);
            mAdapter.setRecipes(recipes);
            binding.pBar.setVisibility(View.INVISIBLE);
        });
    }

    public void verifyPermissions() {
        // This will return the current Status
        int permissionExternalMemory = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, 1);
        }
    }

}

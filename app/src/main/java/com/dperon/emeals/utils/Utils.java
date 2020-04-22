package com.dperon.emeals.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import com.dperon.emeals.R;
import com.dperon.emeals.model.Main;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.model.entities.RecipeEntity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dperon.emeals.viewmodel.MainActivityViewmodel.TAG;

public class Utils {

    public static Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
        Log.d("picassoImageTarget", " picassoImageTarget");
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(() -> {
                    final File myImageFile = new File(directory, imageName); // Create image file
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myImageFile);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                    Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }


    public static List<Recipe> entityToModel(List<RecipeEntity> entities) {
        List<Recipe> list = new ArrayList<>();
        for(RecipeEntity entity : entities){
            Recipe recipe = new Recipe();
            recipe.setMain(new Main());
            recipe.getMain().setTitle(entity.title);
            recipe.getMain().setInstructions(parseMap(entity.getInstructions()));
            recipe.getMain().setIngredients(parseMap(entity.getIngredients()));
            recipe.setId(entity.id);
            list.add(recipe);
        }
        return list;
    }

    private static Map<String, String> parseMap(String string) {
        String[] array = string.split("||");
        Map<String,String> result = new HashMap<>();
        for(int i = 0; i < array.length;i++){
            result.put(String.valueOf(i), array[i]);
        }
        return result;
    }

    public static String mapToString(Map<String, String> map) {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.joining("||"));
    }
}

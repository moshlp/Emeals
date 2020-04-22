package com.dperon.emeals.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dperon.emeals.R;
import com.dperon.emeals.model.Recipe;
import com.dperon.emeals.utils.Constants;
import com.dperon.emeals.utils.Utils;
import com.dperon.emeals.utils.Variables;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeRVAdapter extends
        RecyclerView.Adapter<RecipeRVAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> list = new ArrayList<>();
    private ExpandableAdapter adapter;
    private List<String> listGroup = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public RecipeRVAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        listGroup.add(Constants.ING_TITLE);
        listGroup.add(Constants.INST_TITLE);
        this.onItemClickListener = onItemClickListener;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView picture;
        ExpandableListView expandableListView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            picture = itemView.findViewById(R.id.picture);
            expandableListView = itemView.findViewById(R.id.expandableListView);

        }
        public void bind(final Recipe model,
                         final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(model));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recipe, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe item = list.get(position);
        holder.bind(item, onItemClickListener);
        holder.title.setText(item.getMain().getTitle());
        if (Variables.isNetworkConnected) {
            //download image to storage
            Picasso.get().load(item.getMain().getPrimaryPictureUrl()).into(Utils.picassoImageTarget(context.getApplicationContext(), "imageDir", item.getId() + ".jpeg"));
            Picasso.get().load(item.getMain().getPrimaryPictureUrl()).into(holder.picture);
        } else {
            //display stored image
            ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File myImageFile = new File(directory, item.getId() + ".jpeg");
            Picasso.get().load(myImageFile).into(holder.picture);
        }
        HashMap<String, List<String>> options = new HashMap<>();
        options.put(Constants.INST_TITLE, new ArrayList(item.getMain().getInstructions().values()));
        options.put(Constants.ING_TITLE, new ArrayList(item.getMain().getIngredients().values()));
        adapter = new ExpandableAdapter(context, listGroup, options);
        holder.expandableListView.setAdapter(adapter);

        holder.title.setOnClickListener(v -> {
            Activity activity = (Activity) context;
            AlertDialog.Builder changeTitleAlert = new AlertDialog.Builder(context);
            changeTitleAlert.setTitle("Change title");
            final View customLayout = activity.getLayoutInflater().inflate(R.layout.dialog_title, null);
            changeTitleAlert.setView(customLayout);
            changeTitleAlert.setNeutralButton("OK",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0,
                                            int arg1) {


                        }
                    });
            // create and show the alert dialog
            AlertDialog dialog = changeTitleAlert.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setRecipes(List<Recipe> recipes){
        this.list = recipes;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe model);
    }

}
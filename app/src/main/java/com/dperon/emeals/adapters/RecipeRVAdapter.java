package com.dperon.emeals.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dperon.emeals.R;
import com.dperon.emeals.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRVAdapter extends
        RecyclerView.Adapter<RecipeRVAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> list;
    private OnItemClickListener onItemClickListener;

    public RecipeRVAdapter(Context context, List<Recipe> list,
                           OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView picture;
        ExpandableListView instructions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            picture = itemView.findViewById(R.id.picture);
            instructions = itemView.findViewById(R.id.instructions);
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
        holder.title.setText(item.getPlanTitle());
        Picasso.get().load(item.getMain().getPrimaryPictureUrl()).into(holder.picture);
        HashMap<String, List<String>> instructions = new HashMap<>();
        instructions.put("Instructions", new ArrayList(item.getMain().getInstructions().values()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe model);
    }

}
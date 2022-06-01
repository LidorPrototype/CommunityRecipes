package com.l_es.communityrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.l_es.communityrecipes.R;

import java.util.List;

/**
 * Created by Lidor on 04/20/2022.
 * Developer name: L-ES
 * _        _   _____     ____    ______
 * | |      |_| |  __ \   / __ \  |  O   |
 * | |      | | | |  | | | |  | | |   ___/
 * | |____  | | | |__| | | |__| | | | \
 * |______| |_| |_____/   \____/  |_|__\
 * ____         ____
 * |  __|       |  __|
 * |  __|   _   |__  |
 * |____|  |_|  |____|
 */
public class RVRecipesAdapter extends RecyclerView.Adapter<RVRecipesAdapter.RecipesViewHolder> {

    private final List<String> recipes_names, recipe_images;
    private RVCategoriesAdapter.OnItemClickListener mListener;
    protected LayoutInflater recipesInflater;
    protected Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RVCategoriesAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public static class RecipesViewHolder extends RecyclerView.ViewHolder {

        public TextView recipe_name;
        public ImageView recipe_image;

        public RecipesViewHolder(View itemView, RVCategoriesAdapter.OnItemClickListener _listener) {
            super(itemView);
            recipe_name = itemView.findViewById(R.id.text_view_category_recipes_name);
            recipe_image = itemView.findViewById(R.id.image_view_category_recipes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(_listener != null){
                        int position = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            _listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public RVRecipesAdapter(Context context, List<String> _recipes_names, List<String> _recipe_images){
        this.recipes_names = _recipes_names;
        this.recipe_images = _recipe_images;
        this.context = context;
        this.recipesInflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public RVRecipesAdapter.RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = this.recipesInflater.inflate(R.layout.category_recipes_layout, parent, false);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) v.getLayoutParams();
        int desired_w = (parent.getMeasuredWidth() / 2);
        params.width = desired_w;
        params.height = desired_w;
        v.setLayoutParams(params);
        return new RVRecipesAdapter.RecipesViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(RVRecipesAdapter.RecipesViewHolder holder, int position) {
        if(recipes_names.size() != 0 && position < recipes_names.size()) {
            holder.recipe_name.setText(recipes_names.get(position));
        }else {
            holder.recipe_name.setText(this.context.getResources().getString(R.string.loading));
        }
        if(recipe_images.size() != 0 && position < recipe_images.size()){
            try{
                String item = recipe_images.get(position);
                if (item != null){
                    Glide.with(this.context)
                            .load(item)
                            .into(holder.recipe_image);
                }else {
                    holder.recipe_image.setImageResource(R.drawable.general_food);
                }
            }catch (NullPointerException npe){
                holder.recipe_image.setImageResource(R.drawable.general_food);
            }
        }else {
            holder.recipe_image.setImageResource(R.drawable.general_food);
        }
    }

    @Override
    public int getItemCount() {
        return recipes_names.size();
    }
}

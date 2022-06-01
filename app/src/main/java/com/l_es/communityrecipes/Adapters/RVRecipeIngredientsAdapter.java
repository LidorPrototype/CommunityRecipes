package com.l_es.communityrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.l_es.communityrecipes.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Lidor on 04/24/2022.
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
public class RVRecipeIngredientsAdapter extends RecyclerView.Adapter<RVRecipeIngredientsAdapter.RecipeIngredientsViewHolder> {

    private final List<String> ingredient_names = new ArrayList<String>(), ingredient_amounts = new ArrayList<String>();
    protected LayoutInflater categoriesInflater;
    protected Context context;

    public static class RecipeIngredientsViewHolder extends RecyclerView.ViewHolder {

        public TextView ingredient_name;

        public RecipeIngredientsViewHolder(View itemView) {
            super(itemView);
            ingredient_name = itemView.findViewById(R.id.text_view_ingredient_name);
        }
    }

    public RVRecipeIngredientsAdapter(Context context, Map<String, String> _ingredients){
        this.context = context;
        for (Map.Entry<String, String> entry : _ingredients.entrySet()) {
            this.ingredient_names.add(entry.getKey());
            this.ingredient_amounts.add(entry.getValue());
        }
        Collections.reverse(this.ingredient_names);
        Collections.reverse(this.ingredient_amounts);
        this.categoriesInflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public RVRecipeIngredientsAdapter.RecipeIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = this.categoriesInflater.inflate(R.layout.recipe_ingredients_layout, parent, false);
        return new RVRecipeIngredientsAdapter.RecipeIngredientsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVRecipeIngredientsAdapter.RecipeIngredientsViewHolder holder, int position) {
        String full_ingredient_description = ingredient_names.get(position)
                + ":   "
                + ingredient_amounts.get(position);
        holder.ingredient_name.setText(full_ingredient_description);
    }

    @Override
    public int getItemCount() {
        return ingredient_names.size();
    }
}

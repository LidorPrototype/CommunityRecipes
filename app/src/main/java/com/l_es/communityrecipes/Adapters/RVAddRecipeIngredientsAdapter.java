package com.l_es.communityrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.l_es.communityrecipes.R;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
@SuppressWarnings("FieldCanBeLocal")
public class RVAddRecipeIngredientsAdapter extends RecyclerView.Adapter<RVAddRecipeIngredientsAdapter.RVAddRecipeIngredientsViewHolder> {

    private final List<String> ingredients_names;
    private final List<String> ingredients_amounts;
    private final Map<String, String> ingredients;
    protected LayoutInflater recipeInflater;
    protected Context context;

    public static class RVAddRecipeIngredientsViewHolder extends RecyclerView.ViewHolder {

        public TextView ingredients_name, ingredients_amount;

        public RVAddRecipeIngredientsViewHolder(View itemView) {
            super(itemView);
            ingredients_name = itemView.findViewById(R.id.text_view_ingredient_name);
            ingredients_amount = itemView.findViewById(R.id.text_view_ingredient_amount);
        }
    }

    public RVAddRecipeIngredientsAdapter(Context context, List<String> _ingredients_names, List<String> _ingredients_amounts){
        this.context = context;
        this.ingredients_names = _ingredients_names;
        this.ingredients_amounts = _ingredients_amounts;
        this.ingredients = IntStream.range(0,this.ingredients_names.size()).boxed()
                        .collect(Collectors.toMap(this.ingredients_names::get, this.ingredients_amounts::get));
        this.recipeInflater = LayoutInflater.from(this.context);
    }

    public Map<String, String> getIngredients() {
        return this.ingredients;
    }

    @NonNull
    @Override
    public RVAddRecipeIngredientsAdapter.RVAddRecipeIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = this.recipeInflater.inflate(R.layout.add_recipe_ingredients_layout, parent, false);
        return new RVAddRecipeIngredientsAdapter.RVAddRecipeIngredientsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVAddRecipeIngredientsAdapter.RVAddRecipeIngredientsViewHolder holder, int position) {
        String key_name = this.ingredients_names.get(position);
        String key_amount = this.ingredients.get(key_name);
        holder.ingredients_name.setText(key_name);
        holder.ingredients_amount.setText(key_amount);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
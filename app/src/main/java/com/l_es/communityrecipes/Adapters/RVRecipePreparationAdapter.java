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
import java.util.List;

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
public class RVRecipePreparationAdapter extends RecyclerView.Adapter<RVRecipePreparationAdapter.IndividualRecipesViewHolder> {

    private final List<String> _recipe_preparation_number = new ArrayList<String>(), _recipe_preparation_text = new ArrayList<String>();
    protected LayoutInflater recipeInflater;
    protected Context context;

    public static class IndividualRecipesViewHolder extends RecyclerView.ViewHolder {

        public TextView recipe_preparation_number, recipe_preparation_text;

        public IndividualRecipesViewHolder(View itemView) {
            super(itemView);
            recipe_preparation_number = itemView.findViewById(R.id.text_view_preparation_number);
            recipe_preparation_text = itemView.findViewById(R.id.text_view_preparation_text);

        }
    }

    public RVRecipePreparationAdapter(Context context, List<String> recipe_preparations){
        this.context = context;
        for(int i = 0; i < recipe_preparations.size(); i++){
            String prep_level = (i + 1) + "";
            this._recipe_preparation_number.add(prep_level);
            this._recipe_preparation_text.add(recipe_preparations.get(i));
        }
        this.recipeInflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public RVRecipePreparationAdapter.IndividualRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = this.recipeInflater.inflate(R.layout.recipe_preparation_layout, parent, false);
        return new RVRecipePreparationAdapter.IndividualRecipesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVRecipePreparationAdapter.IndividualRecipesViewHolder holder, int position) {
        holder.recipe_preparation_number.setText(_recipe_preparation_number.get(position));
        holder.recipe_preparation_text.setText(_recipe_preparation_text.get(position));
    }

    @Override
    public int getItemCount() {
        return _recipe_preparation_text.size();
    }
}
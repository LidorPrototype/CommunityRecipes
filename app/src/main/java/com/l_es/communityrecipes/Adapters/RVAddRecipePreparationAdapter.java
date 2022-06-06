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
 *  _        _   _____     ____    ______
 * | |      |_| |  __ \   / __ \  |  O   |
 * | |      | | | |  | | | |  | | |   ___/
 * | |____  | | | |__| | | |__| | | | \
 * |______| |_| |_____/   \____/  |_|__\
 *  ____         ____
 * |  __|       |  __|
 * |  __|   _   |__  |
 * |____|  |_|  |____|
 */
public class RVAddRecipePreparationAdapter extends RecyclerView.Adapter<RVAddRecipePreparationAdapter.AddRecipePreparationViewHolder> {

    private final List<String> preparation_texts = new ArrayList<>();
    protected LayoutInflater recipeInflater;
    protected Context context;

    public static class AddRecipePreparationViewHolder extends RecyclerView.ViewHolder {

        public TextView preparation_level, preparation_text;

        public AddRecipePreparationViewHolder(View itemView) {
            super(itemView);
            preparation_level = itemView.findViewById(R.id.text_view_preparation_level);
            preparation_text = itemView.findViewById(R.id.text_view_preparation_text);
        }
    }

    public RVAddRecipePreparationAdapter(Context context, List<String> _preparation_texts){
        this.context = context;
        preparation_texts.addAll(_preparation_texts);
        this.recipeInflater = LayoutInflater.from(this.context);
    }

    public String getItemAt(int position) {
        return preparation_texts.get(position);
    }

    @NonNull
    @Override
    public RVAddRecipePreparationAdapter.AddRecipePreparationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = this.recipeInflater.inflate(R.layout.add_recipe_preparations_layout, parent, false);
        return new AddRecipePreparationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVAddRecipePreparationAdapter.AddRecipePreparationViewHolder holder, int position) {
        holder.preparation_text.setText(preparation_texts.get(position));
    }

    @Override
    public int getItemCount() {
        return preparation_texts.size();
    }
}
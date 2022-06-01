package com.l_es.communityrecipes.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.l_es.communityrecipes.AddRecipePageActivity;
import com.l_es.communityrecipes.R;

import java.util.List;

/**
 * Created by Lidor on 04/30/2022.
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
// Deprecated!
public class LVAddRecipePreparationAdapter extends ArrayAdapter<String> {

    private List<String> preparation_items;
    protected Context context;

    public LVAddRecipePreparationAdapter(Context context, List<String> preparations) {
        super(context, R.layout.add_recipe_preparations_layout, preparations);
        this.context = context;
        this.preparation_items = preparations;
    }

    @Override
    public int getCount() {
        return preparation_items.size();
    }

    @Override
    public String getItem(int position) {
        return preparation_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.add_recipe_preparations_layout, null);
            TextView textViewPreparationLevel = convertView.findViewById(R.id.text_view_preparation_level);
            TextView textTextPreparation = convertView.findViewById(R.id.text_view_preparation_text);
            ImageView imageViewRemovePreparation = convertView.findViewById(R.id.image_view_remove_preparation);
            String level = position + 1 + ".";
            textViewPreparationLevel.setText(level);
            textTextPreparation.setText(preparation_items.get(position));
            imageViewRemovePreparation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddRecipePageActivity.removePreparation(position);
                }
            });
        }
        return convertView;
    }
}

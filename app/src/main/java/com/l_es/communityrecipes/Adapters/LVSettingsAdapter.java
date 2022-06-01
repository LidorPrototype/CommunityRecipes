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

import com.l_es.communityrecipes.R;

import java.util.List;

/**
 * Created by Lidor on 05/01/2022.
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
public class LVSettingsAdapter extends ArrayAdapter<String> {

    private List<String> settings_names;
    private List<Integer> settings_icons;
    protected Context context;

    public LVSettingsAdapter(Context context, List<Integer> icons, List<String> names) {
        super(context, R.layout.add_recipe_preparations_layout, names);
        this.context = context;
        this.settings_icons = icons;
        this.settings_names = names;
    }

    @Override
    public int getCount() {
        return settings_names.size();
    }

    @Override
    public String getItem(int position) {
        return settings_names.get(position);
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
            convertView = inflater.inflate(R.layout.settings_listview_layout, null);
            ImageView setting_icon = convertView.findViewById(R.id.image_view_settings_icon);
            TextView setting_name = convertView.findViewById(R.id.text_view_settings_name);
            setting_icon.setImageResource(this.settings_icons.get(position));
            setting_name.setText(this.settings_names.get(position));
        }
        return convertView;
    }
}

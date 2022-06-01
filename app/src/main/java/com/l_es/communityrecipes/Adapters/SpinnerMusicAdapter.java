package com.l_es.communityrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l_es.communityrecipes.R;

import java.util.List;

/**
 * Created by Lidor on 05/11/2022.
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
public class SpinnerMusicAdapter extends BaseAdapter {

    private Context context;
    private List<String> songsList;

    public SpinnerMusicAdapter(Context context, List<String> musicList) {
        this.context = context;
        this.songsList = musicList;
    }

    @Override
    public int getCount() {
        return songsList != null ? songsList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return songsList != null ? songsList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater
                .from(context)
                .inflate(R.layout.layout_spinner_music, viewGroup, false);

        TextView txtName = rootView.findViewById(R.id.text_view_song_name);
        txtName.setText(songsList.get(position));

        return rootView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}




























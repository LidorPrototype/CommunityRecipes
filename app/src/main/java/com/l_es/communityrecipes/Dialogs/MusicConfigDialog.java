package com.l_es.communityrecipes.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.res.ResourcesCompat;

import com.l_es.communityrecipes.Adapters.SpinnerMusicAdapter;
import com.l_es.communityrecipes.CustomSpinner;
import com.l_es.communityrecipes.R;
import com.l_es.communityrecipes.Services.SoundService;
import com.l_es.communityrecipes.Utilities;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.ShapeType;

/**
 * Created by Lidor on 05/07/2022.
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
@SuppressWarnings("FieldCanBeLocal")
public class MusicConfigDialog extends AppCompatDialogFragment implements CustomSpinner.OnSpinnerEventsListener {

    private CustomSpinner spinnerSongs;
    private final List<String> songs_names = new ArrayList<>(Utilities.songs.keySet());
    private NeumorphButton neumorphButtonMusicConfig;
    private MusicConfigDialogListener listener;
    private Button buttonOK;
    private SpinnerMusicAdapter musicAdapter;
    private boolean musicState = false;
    private int songChosen = R.raw.order;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dialog_config_music, null);

        builder.setView(view);

        assignIDs(view);
        applyMusicStatusTexts();
        setupSpinner();
        setupOnClicks();

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    private void applyMusicStatusTexts() {
        if(Utilities.isMyServiceRunning(SoundService.class, requireActivity())){
            neumorphButtonMusicConfig.setShapeType(ShapeType.BASIN);
            neumorphButtonMusicConfig.setText(getResources().getString(R.string.music_playing));
        }else {
            neumorphButtonMusicConfig.setShapeType(ShapeType.FLAT);
            neumorphButtonMusicConfig.setText(getResources().getString(R.string.music_not_playing));
        }
    }

    private void assignIDs(View _view_){
        spinnerSongs = _view_.findViewById(R.id.spinner_choose_default_song);
        neumorphButtonMusicConfig = _view_.findViewById(R.id.neumorphic_music_config);
        buttonOK = _view_.findViewById(R.id.button_music_dialog_ok);
    }

    private void setupSpinner() {
        musicAdapter = new SpinnerMusicAdapter(getContext(), songs_names);
        spinnerSongs.setAdapter(musicAdapter);
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int song_raw = mPrefs.getInt(Utilities.SP_MUSIC_SONG, R.raw.order);
        int index = songs_names.indexOf(Utilities.getKeyByValue(Utilities.songs, song_raw));
        spinnerSongs.setSelection(index);
        spinnerSongs.setSpinnerEventsListener(this);
    }

    private void setupOnClicks(){
        Intent musicIntent = new Intent(getActivity(), SoundService.class);
        neumorphButtonMusicConfig.setOnClickListener(view -> {
            if(Utilities.isMyServiceRunning(SoundService.class, requireActivity())){
                neumorphButtonMusicConfig.setShapeType(ShapeType.FLAT);
                neumorphButtonMusicConfig.setText(getResources().getString(R.string.music_not_playing));
                musicState = false;
                requireActivity().stopService(musicIntent);
            }else {
                neumorphButtonMusicConfig.setShapeType(ShapeType.BASIN);
                neumorphButtonMusicConfig.setText(getResources().getString(R.string.music_playing));
                musicState = true;
                listener.applyMusicConfig(musicState, getChosenSong());
                requireActivity().startService(musicIntent);
            }
        });
        buttonOK.setOnClickListener(view -> {
            listener.applyMusicConfig(musicState, getChosenSong());
            dismiss();
        });
    }

    @SuppressWarnings("ConstantConditions")
    private int getChosenSong() {
        String songName = spinnerSongs.getSelectedItem().toString();
        try {
            songChosen = Utilities.songs.get(songName);
        } catch (NullPointerException e) {
            songChosen = R.raw.order;
        }
        return songChosen;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (MusicConfigDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement MusicConfigDialogListener");
        }
    }

    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        spinnerSongs.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_spinner_music_up, null));
    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        spinnerSongs.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_spinner_music_down, null));
    }

    public interface MusicConfigDialogListener{
        void applyMusicConfig(boolean musicState, int songChosen);
    }

}























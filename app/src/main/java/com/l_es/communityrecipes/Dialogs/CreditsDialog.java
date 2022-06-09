package com.l_es.communityrecipes.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatButton;

import com.l_es.communityrecipes.R;

import java.util.Arrays;
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
@SuppressWarnings("FieldCanBeLocal")
public class CreditsDialog extends AppCompatDialogFragment {

    private ListView listViewCredits;
    private List<String> creditors;
    private AppCompatButton buttonOK;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dialog_credits, null);

        builder.setView(view);

        listViewCredits = view.findViewById(R.id.list_view_credits);
        buttonOK = view.findViewById(R.id.button_credits_dialog_ok);

        buttonOK.setOnClickListener(view1 -> dismiss());

        creditors = fillCreditorsList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.layout_listview_credits_dialog,
                creditors);
        listViewCredits.setAdapter(adapter);

        AlertDialog dialog = builder.create();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams params = listViewCredits.getLayoutParams();
        params.height = height / 3;
        listViewCredits.setLayoutParams(params);
        listViewCredits.requestLayout();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    private List<String> fillCreditorsList() {
        return Arrays.asList(
                "Star icons created by Freepik - Flaticon",
                "Food icons created by Freepik - Flaticon",
                "Thank you icons created by Freepik - Flaticon",
                "Music icons created by Freepik - Flaticon",
                "Music icons created by Freepik - Flaticon",
                "Feedback icons created by Freepik - Flaticon",
                "Both of Us Music by madirfan from Pixabay",
                "Ambient Piano & Strings Music by ZakharValaha from Pixabay",
                "Chill Abstract (Intention) Music by Coma-Media from Pixabay",
                "Just Relax Music by Lesfm from Pixabay",
                "Morning Garden - Acoustic Chill Music by Olexy from Pixabay",
                "Order Music by ComaStudio from Pixabay",
                "penguinmusic - Modern Chillout Music by penguinmusic from Pixabay",
                "Sedative Music by Lesfm from Pixabay",
                "Spirit Blossom Music by RomanBelov from Pixabay",
                "The Cradle of Your Soul Music by lemonmusicstudio from Pixabay",
                "Logout icons created by dmitri13 - Flaticon"
        );
    }

}























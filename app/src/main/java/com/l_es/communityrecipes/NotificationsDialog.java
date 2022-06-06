package com.l_es.communityrecipes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.SwitchCompat;

/**
 * Created by Lidor on 05/22/2022.
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
public class NotificationsDialog extends AppCompatDialogFragment {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SwitchCompat switchCompatCookReminder;
    private Button buttonOK;
    private boolean cookReminder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dialog_notifications, null);

        builder.setView(view);

        prefs = requireActivity().getSharedPreferences(Utilities.SP_CREATION_TAG, Context.MODE_PRIVATE);
        editor = prefs.edit();

        switchCompatCookReminder = view.findViewById(R.id.switch_cook_reminder_notification);
        buttonOK = view.findViewById(R.id.button_notifications_dialog_ok);

        cookReminder = prefs.getBoolean(Utilities.SP_NOTIFICATIONS, true);

        switchCompatCookReminder.setChecked(cookReminder);

        switchCompatCookReminder.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            cookReminder = isChecked;
            editor.putBoolean(Utilities.SP_NOTIFICATIONS, cookReminder);
            editor.apply();
        });
        buttonOK.setOnClickListener(view1 -> dismiss());

        AlertDialog dialog = builder.create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

}


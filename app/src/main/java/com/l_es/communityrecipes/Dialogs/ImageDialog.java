package com.l_es.communityrecipes.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.imageview.ShapeableImageView;
import com.l_es.communityrecipes.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by Lidor on 04/29/2022.
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
public class ImageDialog extends AppCompatDialogFragment {

    private static final int MAX_HEIGHT = 500;
    private ImageDialogListener listener;
    private ShapeableImageView recipeImageView;
    private AppCompatButton buttonRetakePhoto, buttonGoodPhoto;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ImageDialogListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement ImageDialogListener Mate!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.recipe_image_dialog_layout, null);

        builder.setView(view);

        assignIDs(view);

        byte[] byteArray = new byte[0];
        if (getArguments() != null) {
            byteArray = getArguments().getByteArray("recipe_image");
        }
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        recipeImageView.setImageBitmap(bmp);

        setupOnClicks();

        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        int dialogWidth = lp.width;
        int dialogHeight = lp.height;

        if(dialogHeight > MAX_HEIGHT) {
            dialog.getWindow().setLayout(dialogWidth,MAX_HEIGHT);
        }

        return dialog;
    }

    public void setupOnClicks(){
        buttonRetakePhoto.setOnClickListener(view -> {
            listener.onRetakeImage();
            dismiss();
        });
        buttonGoodPhoto.setOnClickListener(view -> dismiss());
    }

    public void assignIDs(View _view_){
        recipeImageView = _view_.findViewById(R.id.dialog_recipe_image);
        buttonRetakePhoto = _view_.findViewById(R.id.button_retake_photo);
        buttonGoodPhoto = _view_.findViewById(R.id.button_good_photo);
    }

    public interface ImageDialogListener {
        void onRetakeImage();
    }

    public ImageDialog newInstance(Bitmap bitmap) {
        ImageDialog fragment = new ImageDialog();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Bundle bundle = new Bundle();
        bundle.putByteArray("recipe_image",byteArray);

        fragment.setArguments(bundle);

        return fragment;
    }

}























package com.l_es.communityrecipes.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.l_es.communityrecipes.R;
import com.l_es.communityrecipes.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lidor on 05/15/2022.
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
public class RateRecipeDialog extends AppCompatDialogFragment {

    private LinearLayout linearlayout_container, linearLayoutLike, linearLayoutDislike;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private FirebaseFirestore db;
    private int likes = 0, dislikes = 0;
    private TextView textViewLikes, textViewDislikes;
    private Animation bottomUp, topDown, upBottom, downTop;
    private String recipe_type, category_type, recipe_name;
    private int recipeChoice;

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
//        endAnimations();
        super.onDismiss(dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dialog_rate_recipe, null);

        builder.setView(view);

        db = FirebaseFirestore.getInstance();
        prefs = getActivity().getSharedPreferences(Utilities.SP_RECIPES_TYPE, Context.MODE_PRIVATE);
        editor = prefs.edit();

        assignIDs(view);

        recipe_type  = prefs.getString(Utilities.SP_RECIPES_TYPE, Utilities.NULL);
        category_type  = prefs.getString(Utilities.SP_CATEGORY_TYPE, Utilities.NULL);
        recipe_name  = prefs.getString(Utilities.SP_RECIPE_NAME, Utilities.NULL);
        recipeChoice = prefs.getInt(recipe_name, -1);

        setupLinearLayoutsBG();
        setupAnimationListeners();
        startAnimations();
        fillStatistics();
        setupOnClicks();

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private void setupLinearLayoutsBG() {
        if (recipeChoice == Utilities.LIKE_DISLIKE_INT){
            linearLayoutLike.setBackgroundResource(R.drawable.bg_like);
            linearLayoutDislike.setBackgroundResource(R.drawable.bg_dislike);
        }else if (recipeChoice == Utilities.DISLIKE_INT){
            linearLayoutLike.setBackgroundResource(R.drawable.bg_like);
            linearLayoutDislike.setBackgroundResource(R.drawable.bg_dislike_selected);
        }else if (recipeChoice == Utilities.LIKE_INT){
            linearLayoutLike.setBackgroundResource(R.drawable.bg_like_selected);
            linearLayoutDislike.setBackgroundResource(R.drawable.bg_dislike);
        }
    }

    private void assignIDs(View _view_){
        linearlayout_container = _view_.findViewById(R.id.linearlayout_container);
        linearLayoutLike = _view_.findViewById(R.id.linearlayout_like);
        linearLayoutDislike = _view_.findViewById(R.id.linearlayout_dislike);
        textViewLikes = _view_.findViewById(R.id.text_view_likes);
        textViewDislikes = _view_.findViewById(R.id.text_view_dislikes);
    }

    private void startAnimations(){
        linearLayoutLike.startAnimation(bottomUp);
        linearLayoutDislike.startAnimation(topDown);
    }

    private void endAnimations(){
        linearLayoutLike.startAnimation(upBottom);
        linearLayoutDislike.startAnimation(downTop);
    }

    private void setupAnimationListeners() {
        bottomUp = AnimationUtils.loadAnimation(getContext(),
                R.anim.bottom_up);
        topDown = AnimationUtils.loadAnimation(getContext(),
                R.anim.top_down);
        upBottom = AnimationUtils.loadAnimation(getContext(),
                R.anim.up_bottom);
        downTop = AnimationUtils.loadAnimation(getContext(),
                R.anim.down_top);
        upBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        downTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });;
    }

    private void updateRateSP(int intLikeDislike){
        editor.putInt(recipe_name, intLikeDislike);
        editor.apply();
    }

    private void setupOnClicks() {
        // TODO need to make sure the user can only like or dislike one time each recipe
        linearlayout_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                endAnimations();
                dismiss();
            }
        });
        linearLayoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recipeChoice == Utilities.LIKE_DISLIKE_INT){
                    likes += 1;
                }else if (recipeChoice == Utilities.DISLIKE_INT){
                    likes += 1;
                    dislikes -= 1;
                }else{
                    return;
                }
                updateBackgrounds(Utilities.LIKE_INT);
                updateTexts();
                updateRateSP(Utilities.LIKE_INT);
                updateRecipeStatistics();
            }
        });
        linearLayoutDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recipeChoice == Utilities.LIKE_DISLIKE_INT){
                    dislikes += 1;
                }else if (recipeChoice == Utilities.LIKE_INT){
                    likes -= 1;
                    dislikes += 1;
                }else{
                    return;
                }
                updateBackgrounds(Utilities.DISLIKE_INT);
                updateTexts();
                updateRateSP(Utilities.DISLIKE_INT);
                updateRecipeStatistics();
            }
        });
    }

    private void updateRecipeStatistics() {
        DocumentReference docRef = db.collection(Utilities.RECIPES)
                .document(category_type)
                .collection(recipe_type)
                .document(recipe_name);
        Map<String,Object> updates = new HashMap<>();
        updates.put(Utilities.LIKES, likes);
        updates.put(Utilities.DISLIKES, dislikes);
        docRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Rates Updated !", Toast.LENGTH_SHORT).show();
//                        endAnimations();
                        dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), " Update Failed!\nPlease try again later.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void fillStatistics(){
        prefs = getActivity().getSharedPreferences(Utilities.SP_RECIPES_TYPE, Context.MODE_PRIVATE);
        String recipe_type  = prefs.getString(Utilities.SP_RECIPES_TYPE, Utilities.NULL);
        String category_type  = prefs.getString(Utilities.SP_CATEGORY_TYPE, Utilities.NULL);
        String recipe_name  = prefs.getString(Utilities.SP_RECIPE_NAME, Utilities.NULL);
        db.collection(Utilities.RECIPES)
                .document(category_type)
                .collection(recipe_type)
                .document(recipe_name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            try {
                                likes = Math.toIntExact((long)document.get(Utilities.LIKES));
                                dislikes = Math.toIntExact((long)document.get(Utilities.DISLIKES));
                            }catch (NullPointerException e){
                                likes = 0;
                                dislikes = 0;
                            }
                        }
                        else{
                            likes = 0;
                            dislikes = 0;
                        }
                        updateTexts();
                    }
                });
    }

    private void updateTexts() {
        String str_likes = likes + "";
        String str_dislikes = dislikes + "";
        textViewLikes.setText(str_likes);
        textViewDislikes.setText(str_dislikes);
    }

    private void updateBackgrounds(int rateStatus){
        if (rateStatus == Utilities.LIKE_INT){
            linearLayoutLike.setBackgroundResource(R.drawable.bg_like_selected);
            linearLayoutDislike.setBackgroundResource(R.drawable.bg_dislike);
        }else if (rateStatus == Utilities.DISLIKE_INT){
            linearLayoutLike.setBackgroundResource(R.drawable.bg_like);
            linearLayoutDislike.setBackgroundResource(R.drawable.bg_dislike_selected);
        }
    }

}

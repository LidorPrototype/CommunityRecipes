package com.l_es.communityrecipes;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.l_es.communityrecipes.Services.SoundService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EntranceActivity extends AppCompatActivity {

    private Context context;
    private TextView textViewError;
    private final List<String> category_cuisine = new ArrayList<String>();
    private final List<String> category_meal = new ArrayList<String>();
    private final List<String> category_occasion = new ArrayList<String>();
    private final List<String> featured = new ArrayList<String>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences prefs;
    private boolean notifications_status;

    @Override
    protected void onStop() {
        super.onStop();
        if (notifications_status){
            Utilities.startAlarmBroadcastReceiver(this, Calendar.FRIDAY, 17, 51, 0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utilities.stopAlarmBroadcastReceiver(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        context = this;

        prefs = getSharedPreferences(Utilities.SP_CREATION_TAG, Context.MODE_PRIVATE);
        if (prefs.getBoolean(Utilities.SP_MUSIC_DEFAULT, false)){
            Intent musicIntent = new Intent(this, SoundService.class);
            this.startService(musicIntent);
        }

        notifications_status = prefs.getBoolean(Utilities.SP_NOTIFICATIONS, true);

        // Clear all notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        textViewError = (TextView)findViewById(R.id.textview_error);

        // Activate a thread in order to get all the categories data
        activateThreadData();

        // Set timer and move to next activity
        CountDownTimer timer = setTimerToNextActivity();
        timer.start();

    }

    private void activateThreadData() {
        Runnable ThreadCode = new Runnable() {
            @SuppressLint("UseCompatLoadingForDrawables")
            public void run() {
                // Getting data from FireBase FireStore
                fillCategoriesFireStore();
                fillFeaturedsFireStore();
            }
        };
        Thread thread = new Thread(ThreadCode);
        thread.start();
    }

    private void fillCategoriesFireStore() {
        // Get categories list from FireBase
        db.collection(Utilities.CATEGORIES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String _id = document.getId();
                                Map<String, Object> _data = document.getData();
                                for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                                    String key = entry.getKey();
                                    String value = entry.getValue().toString();
                                    if (_id.equalsIgnoreCase(Utilities.CATEGORY_CUISINE)){
                                        category_cuisine.add(key);
                                    }else if (_id.equalsIgnoreCase(Utilities.CATEGORY_MEAL)){
                                        category_meal.add(key);
                                    }else if (_id.equalsIgnoreCase(Utilities.CATEGORY_OCCASION)){
                                        category_occasion.add(key);
                                    }else{
                                        category_cuisine.add(getResources().getString(R.string._error_) + key);
                                    }
                                }
                            }
                        }
                        else{
                            String err_msg = getResources().getString(R.string._error_) + task.toString();
                            textViewError.setText(err_msg);
                        }
                    }
                });
    }

    private void fillFeaturedsFireStore() {
        // Get featured list from FireBase
        db.collection(Utilities.FEATUREDS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String _id = document.getId();
                                featured.add(_id);
                            }
                        }
                        else{
                            String err_msg = getResources().getString(R.string._error_) + task.toString();
                            textViewError.setText(err_msg);
                        }
                    }
                });
    }

    private CountDownTimer setTimerToNextActivity() {
        return new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) { /* No Need */ }
            public void onFinish() {
                if (category_cuisine.size() != 0 && category_meal.size() != 0 &&
                        category_occasion.size() != 0 && featured.size() != 0){
                    // Activate a background thread to bring all of the recipes
                    // Move Categories and Featureds to SharedPreferences
                    SharedPreferences prefs = getSharedPreferences(Utilities.SP_CATEGORIES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putStringSet(Utilities.CATEGORY_CUISINE, new HashSet<String>(category_cuisine));
                    editor.putStringSet(Utilities.CATEGORY_MEAL, new HashSet<String>(category_meal));
                    editor.putStringSet(Utilities.CATEGORY_OCCASION, new HashSet<String>(category_occasion));
                    editor.apply();
                    // Move to the next activity
                    Utilities.useBungee(context, MainRecipesCategoriesActivity.class, Utilities.ANIMATION_FADE, true);
                    finish();
                }
                else {
                    textViewError.setText(getResources().getString(R.string.loading_categories_error));
                }
            }
        };
    }

}
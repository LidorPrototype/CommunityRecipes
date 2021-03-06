package com.l_es.communityrecipes;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.l_es.communityrecipes.Services.SoundService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class EntranceSplashActivity extends AppCompatActivity {

    private Context context;
    private TextView textViewError;
    private final List<String> category_cuisine = new ArrayList<>();
    private final List<String> category_meal = new ArrayList<>();
    private final List<String> category_occasion = new ArrayList<>();
    private final List<String> featured = new ArrayList<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences prefs;
    private boolean notifications_status;
    private final int counterTime = 5000;

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

        textViewError = findViewById(R.id.textview_error);

        // Activate a thread in order to get all the categories data
        activateThreadData();

        // Set timer and move to next activity
        CountDownTimer timer = setTimerToNextActivity();
        timer.start();

    }

    private void activateThreadData() {
        Runnable ThreadCode = () -> {
            // Getting data from FireBase FireStore
            fillCategoriesFireStore();
            fillFeaturedsFireStore();
        };
        Thread thread = new Thread(ThreadCode);
        thread.start();
    }

    @SuppressWarnings("unused")
    private void fillCategoriesFireStore() {
        // Get categories list from FireBase
        db.collection(Utilities.CATEGORIES)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String _id = document.getId();
                            Map<String, Object> _data = document.getData();
                            for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue().toString();
                                String deviceLanguage = Utilities.getDeviceLanguage();
                                if (_id.equalsIgnoreCase(Utilities.CATEGORY_CUISINE)){
                                    category_cuisine.add(key);
                                }else if (_id.equalsIgnoreCase(Utilities.CATEGORY_MEAL)){
                                    category_meal.add(key);
                                }else if (_id.equalsIgnoreCase(Utilities.CATEGORY_OCCASION)){
                                    category_occasion.add(key);
                                }
                            }
                        }
                    }
                    else{
                        String err_msg = getResources().getString(R.string._error_) + task;
                        textViewError.setText(err_msg);
                    }
                });
    }

    private void fillFeaturedsFireStore() {
        // Get featured list from FireBase
        db.collection(Utilities.FEATUREDS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String _id = document.getId();
                            featured.add(_id);
                        }
                    }
                    else{
                        String err_msg = getResources().getString(R.string._error_) + task;
                        textViewError.setText(err_msg);
                    }
                });
    }

    private CountDownTimer setTimerToNextActivity() {
        return new CountDownTimer(counterTime, 1000) {
            public void onTick(long millisUntilFinished) { /* No Need */ }
            public void onFinish() {
                if (category_cuisine.size() != 0 && category_meal.size() != 0 &&
                        category_occasion.size() != 0 && featured.size() != 0){
                    // Activate a background thread to bring all of the recipes
                    // Move Categories and Featureds to SharedPreferences
                    SharedPreferences prefs = getSharedPreferences(Utilities.SP_CATEGORIES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putStringSet(Utilities.CATEGORY_CUISINE, new HashSet<>(category_cuisine));
                    editor.putStringSet(Utilities.CATEGORY_MEAL, new HashSet<>(category_meal));
                    editor.putStringSet(Utilities.CATEGORY_OCCASION, new HashSet<>(category_occasion));
                    editor.apply();
                    // Move to the next activity
                    Utilities.useBungee(context, MainActivityRecipesCategories.class, Utilities.ANIMATION_FADE, true);
                    finish();
                }
                else {
                    textViewError.setText(getResources().getString(R.string.loading_categories_error));
                }
            }
        };
    }

}
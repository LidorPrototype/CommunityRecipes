package com.l_es.communityrecipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.l_es.communityrecipes.Adapters.LVSettingsAdapter;
import com.l_es.communityrecipes.Dialogs.CreditsDialog;
import com.l_es.communityrecipes.Dialogs.MusicConfigDialog;
import com.l_es.communityrecipes.Services.SoundService;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements MusicConfigDialog.MusicConfigDialogListener {

    private ListView listViewSettings;
    private List<Integer> settings_icons;
    private List<String> settings_names;
    private LVSettingsAdapter lvSettingsAdapter;
    private Context context;
    private CardView cardViewMusic, cardViewCredits;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private TextView textViewMusicStatus;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = this;

        setUniqueToolBar();
        assignIDs();

        prefs = getSharedPreferences(Utilities.SP_CREATION_TAG, Context.MODE_PRIVATE);
        editor = prefs.edit();
        user = FirebaseAuth.getInstance().getCurrentUser();

        boolean playingStatus = Utilities.isMyServiceRunning(SoundService.class, context);
        if (playingStatus){
            textViewMusicStatus.setText(getResources().getString(R.string.music_playing));
        }else{
            textViewMusicStatus.setText(getResources().getString(R.string.music_not_playing));
        }

        setupSettingsList();
        setupOnClicks();

    }

    private void setupSettingsList() {
        settings_icons = new ArrayList<Integer>();
        settings_icons.add(R.drawable.feature);
        settings_icons.add(R.drawable.notifications_24);
        settings_icons.add(R.drawable.add_24);
        settings_icons.add(R.drawable.rate_24);
        settings_icons.add(R.drawable.expand);
        settings_icons.add(R.drawable.logout);
        settings_names = new ArrayList<String>();
        settings_names.add("Get Featured");         //  0
        settings_names.add("Notifications");        //  1
        settings_names.add("Add A Recipe");         //  2
        settings_names.add("Rate Us");              //  3
        settings_names.add("Check Other Apps");     //  4
        if (user != null) {
            settings_names.add("Logout");           //  5
        }else{
            settings_names.add("Login");            //  5
        }
        lvSettingsAdapter = new LVSettingsAdapter(this, settings_icons, settings_names);
        listViewSettings.setAdapter(lvSettingsAdapter);
    }

    private void setupOnClicks() {
        listViewSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0: // Get Featured
                        Toast.makeText(SettingsActivity.this, "Pay Us First Matey !", Toast.LENGTH_SHORT).show();
                        sendEmail();
                        break;
                    case 1: // Notifications
                        NotificationsDialog notificationsDialog = new NotificationsDialog();
                        notificationsDialog.show(getSupportFragmentManager(), "notifications config dialog");
                        break;
                    case 2: // Add A Recipe
                        Utilities.useBungee(context, AddRecipePageActivity.class, Utilities.ANIMATION_ZOOM, true);
                        finish();
                        break;
                    case 3: // Rate Us
                        break;
                    case 4: // Check Other Apps
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=6589420104492859272"));
                        startActivity(intent);
                        finish();
                        break;
                    case 5:
                        if (user != null){
                            FirebaseAuth.getInstance().signOut();
                            Toast.makeText(context, "Signed out", Toast.LENGTH_SHORT).show();
                            Intent intent_signed_out = new Intent(SettingsActivity.this, MainRecipesCategoriesActivity.class);
                            startActivity(intent_signed_out);
                        }else{
                            Utilities.useBungee(context, LoginOrRegisterActivity.class, Utilities.ANIMATION_SLIDE_LEFT, true);
                        }
                        finish();
                        break;
                }
            }
        });
        cardViewMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMusicConfigDialog();
            }
        });
        cardViewCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreditsDialog();
            }
        });
    }

    public void assignIDs(){
        listViewSettings = findViewById(R.id.list_view_settings);
        cardViewCredits = findViewById(R.id.card_view_credits);
        cardViewMusic = findViewById(R.id.card_view_music);
        textViewMusicStatus = findViewById(R.id.text_view_music_status);
    }

    public void setUniqueToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void openCreditsDialog(){
        CreditsDialog creditsDialog = new CreditsDialog();
        creditsDialog.show(getSupportFragmentManager(), "music config dialog");
    }

    private void openMusicConfigDialog() {
        MusicConfigDialog musicConfigDialog = new MusicConfigDialog();
        musicConfigDialog.show(getSupportFragmentManager(), "music config dialog");
    }

    @Override
    public void applyMusicConfig(boolean musicState, int songChosen) {
        editor.putBoolean(Utilities.SP_MUSIC_DEFAULT, musicState);
        editor.putInt(Utilities.SP_MUSIC_SONG, songChosen);
        editor.apply();
    }

    protected void sendEmail() {
        String[] TO = {Utilities.EMAIL_TO};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Utilities.EMAIL_SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Utilities.EMAIL_MSG);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_FADE, false);
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SettingsActivity.this,
                    "There is no email client installed mate.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_FADE, false);
    }

}













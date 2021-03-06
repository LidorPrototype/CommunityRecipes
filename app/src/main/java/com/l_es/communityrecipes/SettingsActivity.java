package com.l_es.communityrecipes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
import com.l_es.communityrecipes.Dialogs.NotificationsDialog;
import com.l_es.communityrecipes.Services.SoundService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
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
        settings_icons = new ArrayList<>();
        settings_icons.add(R.drawable.notifications_24);
        settings_icons.add(R.drawable.add_24);
        settings_icons.add(R.drawable.rate_24);
        settings_icons.add(R.drawable.expand);
        settings_icons.add(R.drawable.logout);
        settings_names = new ArrayList<>();
        settings_names.add(getResources().getString(R.string.notifications));        //  0
        settings_names.add(getResources().getString(R.string.add_a_recipe));         //  1
        settings_names.add(getResources().getString(R.string.rate_us));              //  2
        settings_names.add(getResources().getString(R.string.check_other_apps));     //  3
        if (user != null) {
            settings_names.add(getResources().getString(R.string.logout));           //  4
        }else{
            settings_names.add(getResources().getString(R.string.login));            //  4
        }
        lvSettingsAdapter = new LVSettingsAdapter(this, settings_icons, settings_names);
        listViewSettings.setAdapter(lvSettingsAdapter);
    }

    private void setupOnClicks() {
        listViewSettings.setOnItemClickListener((adapterView, view, position, l) -> {
            switch (position){
                case 0: // Notifications
                    NotificationsDialog notificationsDialog = new NotificationsDialog();
                    notificationsDialog.show(getSupportFragmentManager(), "notifications config dialog");
                    break;
                case 1: // Add A Recipe
                    Utilities.useBungee(context, AddRecipePageActivity.class, Utilities.ANIMATION_ZOOM, true);
                    finish();
                    break;
                case 2: // Rate Us
                    break;
                case 3: // Check Other Apps
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=6589420104492859272"));
                    startActivity(intent);
                    finish();
                    break;
                case 4:
                    if (user != null){
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(context, getResources().getString(R.string.signed_out), Toast.LENGTH_SHORT).show();
                        Intent intent_signed_out = new Intent(SettingsActivity.this, MainActivityRecipesCategories.class);
                        startActivity(intent_signed_out);
                    }else{
                        Utilities.useBungee(context, LoginOrRegisterActivity.class, Utilities.ANIMATION_SLIDE_LEFT, true);
                    }
                    finish();
                    break;
            }
        });
        cardViewMusic.setOnClickListener(view -> openMusicConfigDialog());
        cardViewCredits.setOnClickListener(view -> openCreditsDialog());
    }

    public void assignIDs(){
        listViewSettings = findViewById(R.id.list_view_settings);
        cardViewCredits = findViewById(R.id.card_view_credits);
        cardViewMusic = findViewById(R.id.card_view_music);
        textViewMusicStatus = findViewById(R.id.text_view_music_status);
    }

    public void setUniqueToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        toolbar.setTitle(getResources().getString(R.string.settings));
        toolbar.setTitleTextAppearance(this, R.style.ToolbarAppearance);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void openCreditsDialog(){
        CreditsDialog creditsDialog = new CreditsDialog();
        creditsDialog.show(getSupportFragmentManager(), "credits dialog");
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

    @SuppressLint("IntentReset")
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
            Toast.makeText(SettingsActivity.this, getResources().getString(R.string.email_exception), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_FADE, false);
    }

}













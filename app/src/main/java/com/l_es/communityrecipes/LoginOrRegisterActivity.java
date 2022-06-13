package com.l_es.communityrecipes;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginOrRegisterActivity extends AppCompatActivity {

    private AppCompatButton buttonLogin, buttonRegister;
    private TextView textViewEmail, textViewPassword;
    private String email, password;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        mAuth = FirebaseAuth.getInstance();

        setUniqueToolBar();
        assignIDs();
        setupOnClicks();

    }

    private void setupOnClicks() {
        buttonLogin.setOnClickListener(view -> {
            getCredentials();
            signInUser(email, password);
        });
        buttonRegister.setOnClickListener(view -> {
            getCredentials();
            if(checkCredentials()) {
                createNewUser(email, password);
            }
        });
    }

    private boolean checkCredentials() {
        if (email.split("@")[1].contains(email.split("@")[1])){
            Toast.makeText(this, getResources().getString(R.string.email_not_valid), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < Utilities.PASSWORD_LENGTH){
            Toast.makeText(this, getResources().getString(R.string.password_length_error), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getCredentials() {
        email = textViewEmail.getText().toString();
        password = textViewPassword.getText().toString();
    }

    private void createNewUser(String email_, String password_) {
        mAuth.createUserWithEmailAndPassword(email_, password_)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(Utilities.LOG_FLAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(LoginOrRegisterActivity.this, getResources().getString(R.string.email_in_use_msg),
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            updateUI(null);
                        }
                    }
                });
    }

    private void signInUser(String email_, String password_){
        mAuth.signInWithEmailAndPassword(email_, password_)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(Utilities.LOG_FLAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(Utilities.LOG_FLAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginOrRegisterActivity.this, getResources().getString(R.string.authentication_failed),
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user_){
        if (user_ == null){
            Toast.makeText(this, getResources().getString(R.string.login_fail_msg), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getResources().getString(R.string.login_logged_in_msg), Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    private void assignIDs() {
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);
        textViewEmail = findViewById(R.id.editTextEmail);
        textViewPassword = findViewById(R.id.editTextPassword);
    }

    public void setUniqueToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar_login_or_register);
        toolbar.setTitle(getResources().getString(R.string.login_or_register));
        toolbar.setTitleTextAppearance(this, R.style.ToolbarAppearance);
        setSupportActionBar(toolbar);
        try {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException ignored){ }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_SLIDE_RIGHT, false);
    }
}
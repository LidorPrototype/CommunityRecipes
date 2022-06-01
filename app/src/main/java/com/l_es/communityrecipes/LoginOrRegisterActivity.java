package com.l_es.communityrecipes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class LoginOrRegisterActivity extends AppCompatActivity {

    private Button buttonLogin, buttonRegister;
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
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = textViewEmail.getText().toString();
                password = textViewPassword.getText().toString();
                signInUser(email, password);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = textViewEmail.getText().toString();
                password = textViewPassword.getText().toString();
                createNewUser(email, password);
            }
        });
    }

    private void createNewUser(String email_, String password_) {
        mAuth.createUserWithEmailAndPassword(email_, password_)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(Utilities.LOG_FLAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(LoginOrRegisterActivity.this, "Email is already in use, try again mate...",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                updateUI(null);
                            }
                        }
                    }
                });
    }

    private void signInUser(String email_, String password_){
        mAuth.signInWithEmailAndPassword(email_, password_)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(Utilities.LOG_FLAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(Utilities.LOG_FLAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginOrRegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user_){
        if (user_ == null){
            Toast.makeText(this, "Something has failed, please try again later...", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "You are already logged in mate !", Toast.LENGTH_SHORT).show();
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
        toolbar.setTitle("Login or Register");
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException ignored){ }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_SLIDE_RIGHT, false);
    }
}
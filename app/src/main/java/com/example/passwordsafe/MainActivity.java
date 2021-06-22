package com.example.passwordsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    //UI Views
    private TextView authStatusTv;
    private Button authBtn;
    private AnimationDrawable animationDrawable;
    private LinearLayout linearLayout;
    private Executor ex;
    private BiometricPrompt bpromt;
    BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gradient animation code
        // init constraintLayout
        linearLayout = (LinearLayout) findViewById(R.id.mylinearmainlayout);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);
        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);



        //init UI Views
        authStatusTv = findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);



        //init Biometric
        ex = ContextCompat.getMainExecutor(this);
        bpromt = new BiometricPrompt(MainActivity.this, ex, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

                //error in authentication, stop tasks that requires auth
                authStatusTv.setText("Authentication error! " + errString);
                Toast.makeText(MainActivity.this,"Authentication error! " + errString,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                //authentication succeed, continue tasks that requires auth
                authStatusTv.setText("Authentication successful! ");
                startActivity(new Intent(MainActivity.this, Menu.class));
                Toast.makeText(MainActivity.this,"Authentication successful! ",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

                //failed authenticating, stop tasks that requires auth
                authStatusTv.setText("Authentication failed! ");
                Toast.makeText(MainActivity.this,"Authentication failed! ",Toast.LENGTH_SHORT).show();

            }
        });

        //setup title, description on auth dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("PasswordSafe")
                .setSubtitle("Login using fingerprint authentication.\nFingerprint need to be registered to the device beforehand.")
                .setNegativeButtonText("Cancel")
                .build();
        //handle authBtn click, start authentication
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bpromt.authenticate(promptInfo);
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}

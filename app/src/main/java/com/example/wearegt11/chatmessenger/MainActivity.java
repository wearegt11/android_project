package com.example.wearegt11.chatmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents
            displayChatMessages();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private void displayChatMessages() {

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode,
        Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == SIGN_IN_REQUEST_CODE) {
                if(resultCode == RESULT_OK) {
                    Toast.makeText(this,
                            "Successfully signed in. Welcome!",
                            Toast.LENGTH_LONG)
                            .show();
                    displayChatMessages();
                } else {
                    Toast.makeText(this,
                            "We couldn't sign you in. Please try again later.",
                            Toast.LENGTH_LONG)
                            .show();

                    // Close the app
                    finish();
                }
            }

        }

    }
}

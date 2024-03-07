package com.example.mrk201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }

    //BACK PRESS
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Profile.this,MainActivity.class);

        startActivity(intent);
        finish();
    }
}
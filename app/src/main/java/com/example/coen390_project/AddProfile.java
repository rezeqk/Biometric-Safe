package com.example.coen390_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        getSupportActionBar().setTitle("Add Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
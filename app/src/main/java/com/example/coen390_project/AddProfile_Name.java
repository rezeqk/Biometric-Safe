package com.example.coen390_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddProfile_Name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_name);
        getSupportActionBar().setTitle("Add Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
package com.example.coen390_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Checkstatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstatus);
        getSupportActionBar().setTitle("Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
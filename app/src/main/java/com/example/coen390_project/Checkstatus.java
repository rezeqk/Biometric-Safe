package com.example.coen390_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Checkstatus extends AppCompatActivity {

    static Boolean safe_status;
    static double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstatus);
        getSupportActionBar().setTitle("Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
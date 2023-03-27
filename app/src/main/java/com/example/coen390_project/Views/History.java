package com.example.coen390_project.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.coen390_project.R;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");
    }
}
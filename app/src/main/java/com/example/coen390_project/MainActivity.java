package com.example.coen390_project;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // initializing buttons
    Button unlock;
    Button profile_add;
    Button profile_view;
    Button status_check;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Add a title to the bar
        getSupportActionBar().setTitle("Main Menu");
        // create a method
        unlock = (Button)findViewById(R.id.unblocker);
        profile_view=(Button)findViewById(R.id.view_profile);
        status_check=(Button) findViewById(R.id.check_status);
        profile_add=(Button) findViewById(R.id.add_profile);

        //after clicking button to direct to a different page

        profile_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProfile.class);
                        startActivity(intent);
            }
        });
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Unblock.class);
                startActivity(intent);
            }
        });
        status_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Checkstatus.class);
                startActivity(intent);
            }
        });
        profile_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Viewprofile.class);
                startActivity(intent);
            }
        });


    }
}
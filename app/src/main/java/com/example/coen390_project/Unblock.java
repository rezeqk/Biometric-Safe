package com.example.coen390_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Unblock extends AppCompatActivity {

    // initializing buttons
    Button unlock_button;
    Button lock_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unblock);
        getSupportActionBar().setTitle("Unblock");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        unlock_button = (Button)findViewById(R.id.unlock_button);
        lock_button=(Button)findViewById(R.id.lock_button);

        //when you click the button gets a message saying that the safe is locked/unlocked

        unlock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(Unblock.this,"The safe is unlocked", Toast.LENGTH_SHORT);
                    toast.show();
//                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START,100,0);
//                toast.show();
            }
        });

        lock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Unblock.this,"The safe is locked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
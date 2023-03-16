package com.example.coen390_project.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coen390_project.R;


public class AddProfile_Instruction extends AppCompatActivity {

    Button button_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_instruction);
        button_create=(Button) findViewById(R.id.create_button);

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProfile_Instruction.this, AddProfile_Name.class);
                startActivity(intent);
            }
        });
    }
}
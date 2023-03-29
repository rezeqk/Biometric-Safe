package com.example.coen390_project.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProfile_Name extends AppCompatActivity {

    Button save;
    EditText name_creation;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_name);
        getSupportActionBar().setTitle("Add Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save = (Button) findViewById(R.id.save_button);
        name_creation=(EditText)findViewById(R.id.name);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get name from text box
                String name = name_creation.getText().toString();

                //check if name is taken
                User tempUser = new User();
                Boolean nameTaken = (tempUser.allUsers).containsValue(name);

                if(nameTaken){
                    Toast.makeText(AddProfile_Name.this, "This name is already taken.", Toast.LENGTH_SHORT).show();
                }else {
                    //continue with new profile creation
                    Intent intent = new Intent(AddProfile_Name.this, AddProfile_Instruction.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });
    }

}
package com.example.coen390_project.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//
//                myRef.setValue("The name is saved");
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_creation.getText().toString();
                Intent intent = new Intent(AddProfile_Name.this, AddProfile_Instruction.class);
                intent.putExtra("name", name);
                startActivity(intent);
                //writeNewUser();
            }
        });
    }
    //to write the user to firebase database
//    public void writeNewUser() {
//        User user = new User(name_creation.getText().toString());
//        //mDatabase.child("Users").child(user.getName()).setValue(user);
//        DatabaseReference newUserRef = mDatabase.child("Users").child(String.valueOf(counter()));
//        newUserRef.setValue(user.getName());
//        //mDatabase.child("Users").put("6",user.getName());
//        Intent intent = new Intent(AddProfile_Name.this, AddProfile_Instruction.class);
//        startActivity(intent);
//    }
//
//    public int counter(){
//        User tempUser = new User();
//        int thisID = tempUser.getNextID();
//        int nextID = thisID + 1;
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("nextID");
//        myRef.setValue(String.valueOf(nextID), "nextID");
//        return thisID;
//    }
}
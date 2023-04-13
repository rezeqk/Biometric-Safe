/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

AddProfile_Instruction.java
This activity class provides users instructions on how to enroll a fingerprint and
initiates enrollment process on physical safe
*/

package com.example.coen390_project.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class AddProfile_Instruction extends AppCompatActivity {

    Button button_create;
    FirebaseDatabase database;
    User tempUser = new User();     //used to access the user ID
    int newUserID;
    String newUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_instruction);
        button_create=(Button) findViewById(R.id.create_button);
        database = FirebaseDatabase.getInstance();

            //this button is used to initiate enrollment
            button_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //disable button so people can't press the button twice
                    button_create.setEnabled(false);

                    //get the name from the user (which they entered on the previous page)
                    Intent intent = getIntent();
                    newUserName = intent.getStringExtra("name");

                    //get the new ID
                    newUserID = tempUser.getNextID();

                    //tell microcontroller to start enrolling through Firebase database
                    DatabaseReference myRef = database.getReference("enrolling");
                    DatabaseReference myRef2 = database.getReference("SafeAction");
                    myRef.setValue(String.valueOf(newUserID), "enrolling");
                    myRef2.setValue("enroll");
                }
            });

            //check for successful enrollment
            DatabaseReference myRef3 = database.getReference("enrolling");
            myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String result = (String) snapshot.getValue();
                if(result.equals("success")){
                    //fingerprint successfully added, add user to Firebase
                    addUserToFirebase(newUserName, newUserID);

                    //adjust the next ID in firebase
                    int nextID = newUserID + 1;
                    DatabaseReference nextIDReference = database.getReference("nextID");
                    nextIDReference.setValue(String.valueOf(nextID));

                    //set enrolling back to neutral so physical safe leaves enrolling mode
                    myRef3.setValue("-");

                    //toast that enrollment was successful
                    Toast.makeText(AddProfile_Instruction.this, "New profile added! Return to home page.", Toast.LENGTH_SHORT).show();
                }
                else if(result.equals("failure")){
                    //set enrolling back to neutral
                    myRef3.setValue("-");

                    //toast that there was an error enrolling
                    Toast.makeText(AddProfile_Instruction.this, "Failed to add fingerprint", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    void addUserToFirebase(String name, int ID){
        //access database and add a key value pair, where the key is the ID of the new user
        DatabaseReference databaseRef = database.getReference();
        DatabaseReference newUserRef = databaseRef.child("Users").child(String.valueOf(ID));
        newUserRef.setValue(name);
    }
}
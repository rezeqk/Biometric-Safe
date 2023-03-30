package com.example.coen390_project.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddProfile_Instruction extends AppCompatActivity {

    Button button_create;

// delete later
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_instruction);
        button_create=(Button) findViewById(R.id.create_button);

            button_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_create.setEnabled(false);


                    User tempUser = new User();
                    int thisID = tempUser.getNextID() - 1;
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("enrolling");
                    DatabaseReference myRef2 = database.getReference("SafeAction");

                    myRef.setValue(String.valueOf(thisID), "enrolling");
                    myRef2.setValue("enroll");

                }
            });
    }
}
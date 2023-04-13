/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

MainActivity.java
This activity class is the main page of the app. It serves 3 purposes:
1 - to navigate to other pages within the app
2 - to display the current safe status (locked vs unlocked)
3 - to initiate reading from Firebase Database
*/

package com.example.coen390_project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // initializing buttons
    ImageButton profile_add;
    ImageButton profile_view;
    ImageView status_check;
    ImageButton history;

    //initializing text views
    TextView unlock_stat;
    TextView lock_stat;

    //TAG used to display errors with Firebase
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add a title to the bar
        getSupportActionBar().setTitle("Main Menu");
        getSupportActionBar().hide();
        profile_view=(ImageButton) findViewById(R.id.view_profile_button);
        status_check=(ImageView) findViewById(R.id.check_status_button);
        profile_add=(ImageButton) findViewById(R.id.add_profile_button);
        unlock_stat=(TextView)findViewById(R.id.unlock_status);
        lock_stat=(TextView)findViewById(R.id.lock_status);
        history = (ImageButton) findViewById(R.id.history_button);

        //this function is used to read from Firebase Database
        basicRead();

        //after clicking button to direct to a different page
        profile_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProfile_Name.class);
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

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, History.class);
                startActivity(intent);
            }
        });
    }

    //read from Firebase Database
    public void basicRead() {
        //get database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                //extract data from Firebase using a map
                Map<String, String> value = (Map<String, String>) dataSnapshot.getValue();
                String door_status="";
                String invalidFingerprint = "";
                User tempUser = new User();
                tempUser.setNextID(Integer.parseInt(value.get("nextID")));
                if(value !=null ){
                    door_status = value.get("Door Status");
                    invalidFingerprint = value.get("InvalidFingerprint");
                }

                //to change locked/unlocked status photo and textview
                if(door_status.equals("Locked")){
                    status_check.setImageResource(R.drawable.check_status_lock);
                    lock_stat.setText("Locked");
                    System.out.println("status is locked");
                }
                else {
                    status_check.setImageResource(R.drawable.check_status_unlocked);
                    lock_stat.setText("Unlocked");
                    System.out.println("door is Unlocked");
                }

                //to send toast message when invalid fingerprint
                if(invalidFingerprint.equals("true")){
                    String toastMessage = "Invalid fingerprint scanned on safe.";
                    Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("InvalidFingerprint");
                    myRef.setValue("false");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
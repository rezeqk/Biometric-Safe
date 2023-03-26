package com.example.coen390_project.Views;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class Viewprofile extends AppCompatActivity {


    ListView list_view;

    DatabaseReference reference;

    //MyAdapter myAdapter;


    //FirebaseDatabase firebaseDatabase;

    String TAG = "TAG";

    final ArrayList<String>list = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        getSupportActionBar().setTitle("View Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view= findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_profile,list);
        list_view.setAdapter(arrayAdapter);
        basicReadWrite();
    }
    public void basicReadWrite() {
        // [START write_message]
        // Write a message to the database
        reference = FirebaseDatabase.getInstance().getReference().child("User");

//        myRef.setValue("Hello, World!");
        // [END write_message]

        // [START read_message]
        // Read from the database
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, String> users = (Map<String, String>) dataSnapshot.getValue();
             //   User tempUser = new User();
            //    tempUser.setNextID(Integer.parseInt(users.get("nextID")));
                User temp = new User();
                int numUsers = temp.getNextID() - 1;
                if(users !=null ){
                    for(int i=1; i<=numUsers; i++){
                        String ccurrentName = users.get(String.valueOf(i));
                        list.add(ccurrentName);
                        System.out.println(ccurrentName);
                        System.out.println("hello");
                    }
                    //door_status = value.get("Door Status");

                    //invalidFingerprint = value.get("InvalidFingerprint");
                }



                Log.d(TAG, "Value is: " + users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // [END read_message]
    }
}

        /*
        database = FirebaseDatabase.getInstance().getReference().child("Users");

         list_view = (ListView) findViewById(R.id.listview);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                list.add(value);
                arrayAdapter = new ArrayAdapter<String>(Viewprofile.this, android.R.layout.simple_list_item_1, list);

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

         */

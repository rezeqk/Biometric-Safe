/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

History.java
This activity class allows users to see who opened the safe and when, when it was closed, and
whether an item was added or removed
*/

package com.example.coen390_project.Views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ListView list_view_history;
    DatabaseReference reference;

    //this is the list of events that will be displayed
    final ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view_history= findViewById(R.id.listview_history);

        //array adapter is used to connect list view to be displayed with array list that contains data
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        list_view_history.setAdapter(arrayAdapter);

        //get database reference to access history of events from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("History");

        //add child event listener so that the list updates real time
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String historylog = snapshot.getValue(String.class);

                //if the history element indicates that someone opened the safe,
                //indicate who opened it
                if(historylog.contains("Opened")) {
                    //message is sent in the form "Opened~userID~timestamp"
                    String[] ReceivedValues = historylog.split("~");

                    //temp user is used to access the list of users in order to get name from ID
                    User temp = new User();
                    String name = (temp.allUsers).get(ReceivedValues[1]);

                    //we want to display "Opened by USER NAME at TIME"
                    String toDisplay = "Opened by " + name + ReceivedValues[2];

                    //add event to list to display
                    list.add(0,toDisplay);
                }
                else{
                    //other events are added to list directly (they do not need to be edited before adding)
                    list.add(0,historylog);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            //remaining override functions not used
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
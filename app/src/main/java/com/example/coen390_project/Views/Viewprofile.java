/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

Viewprofile.java
This class is used to create the list view with all of the active profiles.
It also allows user to delete profiles
*/

package com.example.coen390_project.Views;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Controllers.MyCustomAdapter;
import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Viewprofile extends AppCompatActivity {
    ListView list_view;
    DatabaseReference reference;
    final ArrayList<String> list = new ArrayList<>();    //contains list of profiles

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        //get firebase reference to access database
        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        //adapter is used to update list view
        final MyCustomAdapter arrayAdapter = new MyCustomAdapter(this, list, reference);
        getSupportActionBar().setTitle("View Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view = findViewById(R.id.listview);
        list_view.setAdapter(arrayAdapter);

        //whenever a user is added or deleted, we want to update list real time
        // so we use a child event to listener
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //when a user is added, add them to the hashmap with all users and
                // any active users (users that aren't deleted), add them to list
                String user = snapshot.getValue(String.class);
                String key = snapshot.getKey();
                (User.allUsers).put(key, user);

                //update list view
                list.clear();
                User tempUser = new User();
                int length = (tempUser.allUsers).size();

                for(int i = 1;i<length+1;i++){
                    String name = (tempUser.allUsers).get(String.valueOf(i));

                    if(!name.contains("deleted")){
                        list.add(name);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //when a user name is changed, it means they are deleted
                //so, we update the list view to remove deleted user from list
                String user = snapshot.getValue(String.class);
                String key = snapshot.getKey();
                (User.allUsers).put(key, user);

                //update list view
                list.clear();
                User tempUser = new User();
                int length = (tempUser.allUsers).size();

                for(int i = 1;i<length+1;i++){
                    String name = (tempUser.allUsers).get(String.valueOf(i));

                    if(!name.contains("deleted")){
                        list.add(name);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //not used

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //not used
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //not used
            }
        });
    }
}
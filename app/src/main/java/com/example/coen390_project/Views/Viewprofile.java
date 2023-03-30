package com.example.coen390_project.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Map;


public class Viewprofile extends AppCompatActivity {


    ListView list_view;

    DatabaseReference reference;


    String TAG = "TAG";

    Context context;

    final ArrayList<String> list = new ArrayList<>();

    //ActivityDeleteDataBinding binding;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button deleteButton = (Button) findViewById(R.id.btn);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        getSupportActionBar().setTitle("View Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view = findViewById(R.id.listview);
        final MyCustomAdapter arrayAdapter = new MyCustomAdapter(this, list, reference);
        list_view.setAdapter(arrayAdapter);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String user = snapshot.getValue(String.class);
                list.add(user);

                // added test
                String key = snapshot.getKey();
                (User.allUsers).put(key, user);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //Handle buttons and add onClickListeners
                //Button deleteBtn= (Button)view.findViewById(R.id.btn);

                String user = snapshot.getValue(String.class);
                list.remove(user);
                arrayAdapter.notifyDataSetChanged();
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

/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

MyCustomAdapter.java
This class is used to create the list view with user names and clickable delete buttons in the
viewProfiles activity
*/

package com.example.coen390_project.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

/*
    extends ArrayAdapter because we are using ListView
    ArrayAdapter converts an ArrayList User object into View items
    loaded into the ListView container
 */
public class MyCustomAdapter extends ArrayAdapter<String> {

    DatabaseReference reference;
    DatabaseReference safeActionReference = FirebaseDatabase.getInstance().getReference("SafeAction");
    DatabaseReference fingerPrintDeleteRef = FirebaseDatabase.getInstance().getReference("fingerprintToDelete");

    public MyCustomAdapter(Context context, ArrayList<String> list, DatabaseReference reference) {
        super(context, 0, list);
        this.reference = reference;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_profile, parent, false);
        }

        //Handle TextView and display string from your list
        TextView profile = (TextView) convertView.findViewById(R.id.label);
        profile.setText(getItem(position));

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn= (ImageButton)convertView.findViewById(R.id.btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Iterate through map to find key of user
                for (Map.Entry<String, String> entry : User.allUsers.entrySet()) {
                    if (entry.getValue().equals(getItem(position))) {
                        System.out.println(entry.getValue() + " value " + entry.getKey());
                        reference.child(entry.getKey()).setValue(entry.getValue().toString()+ " (deleted profile)");
                        fingerPrintDeleteRef.setValue(Integer.parseInt(entry.getKey()));
                        safeActionReference.setValue("delete");
                    }
                }
            }
        });

        return convertView;
    }
}

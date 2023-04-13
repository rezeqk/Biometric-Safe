/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

MyAdapter.java
This class is used to create the custom adapter with user names and clickable delete buttons in the
viewProfiles activity
*/

package com.example.coen390_project.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;

    ArrayList<User> list;

    //constructor
    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list=list;
    }

    //this is used to add a user to the list
    public void add(User user){
        list.add(user);
        notifyDataSetChanged();
    }

    //set layout
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_profile,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            User temp = list.get(position);
            holder.name.setText(temp.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }
    }
}

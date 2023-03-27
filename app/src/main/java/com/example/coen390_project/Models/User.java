package com.example.coen390_project.Models;

import java.util.ArrayList;

public class User {

    //assigns an ID to the user
    private int id;

    // the name of the user
    public String name;

    static int nextID=0;

    // added test
    public static ArrayList<String> allUsers = new ArrayList<String>();

    //constructor
    public User(String name) {
        //this.id = id;
        this.name = name;
    }
    // default constructor
    public User(){}

    // setters and getters
    public void setId(int id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public int getId() {return id;}

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        User.nextID = nextID;
    }
}

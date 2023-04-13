/*
COEN/ELEC 390
Winter 2023
Team 3 - Smart Safe

User.java
This class is used to store all safe user's names and id's
*/
package com.example.coen390_project.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    //user ID
    private int id;

    //the name of the user
    public String name;

    static int nextID;

    //this hashmap stores all of the users that are currently in database
    public static HashMap<String, String> allUsers = new HashMap<String, String>();

    //constructor that automatically assigns id based on next ID
    public User(String name) {
        this.id = nextID;
        this.name = name;
    }

    //constructor that takes ID and name and creates a user
    public User(int ID, String name){
        this.id = ID;
        this.name = name;
    }

    // default empty constructor - required for Firebase
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

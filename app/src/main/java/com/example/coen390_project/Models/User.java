package com.example.coen390_project.Models;

public class User {

    //assigns an ID to the user
    private int id;

    // the name of the user
    private String name;

    static int nextID=0;

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

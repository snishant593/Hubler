package com.demo.nishant.hubbler;


import java.util.ArrayList;

public class Singleton  {

    private static Singleton mInstance;
    private ArrayList<String> list = null;

    public static Singleton getInstance() {
        if(mInstance == null)
            mInstance = new Singleton();

        return mInstance;
    }

    private Singleton() {
        list = new ArrayList<String>();
    }
    // retrieve array from anywhere
    public ArrayList<String> getArray() {
        return this.list;
    }
    //Add element to array
    public void addToArray(String value) {
        list.add(value);
    }
}


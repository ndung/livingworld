package com.livingworld.clients.master.model;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Master {

    int id;
    String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

package com.livingworld.clients.master.model;

import java.io.Serializable;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Master implements Serializable {

    private String id;
    private String name;

    public Master(){

    }

    public Master(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}

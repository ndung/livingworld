package com.livingworld.clients.inbox.model;

import java.io.Serializable;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Inbox implements Serializable{

    private Integer id;
    private String title;
    private String message;
    private String date;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

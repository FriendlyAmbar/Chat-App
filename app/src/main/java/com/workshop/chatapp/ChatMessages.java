package com.workshop.chatapp;

/**
 * Created by ambar on 02-04-2017.
 */

public class ChatMessages {
    String name;
    String message;

    public ChatMessages(){
    }

    public ChatMessages(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}



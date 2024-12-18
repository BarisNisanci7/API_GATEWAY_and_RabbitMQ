package com.rabbitmq.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;

    // Constructor
    public Notification(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

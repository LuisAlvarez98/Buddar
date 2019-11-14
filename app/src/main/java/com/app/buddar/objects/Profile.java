package com.app.buddar.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("user")
    @Expose
    User user;

    public Profile() {
    }

    public Profile(int status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

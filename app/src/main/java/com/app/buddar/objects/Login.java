package com.app.buddar.objects;

public class Login {
    private int status;
    private String message;
    private String token;

    public Login() {
    }

    public Login(int status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

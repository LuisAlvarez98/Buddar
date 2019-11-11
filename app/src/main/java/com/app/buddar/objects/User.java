package com.app.buddar.objects;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String first_name;
    private String last_name;
    private String token;
    public User(){

    }
    public User(String username, String password, String email, String phone, String first_name, String last_name, String token) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

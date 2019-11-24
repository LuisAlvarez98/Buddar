package com.app.buddar.objects;

/**
 * Connection Item OBJ Class
 * Class by: Luis Felipe Alvarez Sanchez
 */
public class Connection {
    private String name;
    private String bio;
    private String picture;
    private String email;

    public Connection(){

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

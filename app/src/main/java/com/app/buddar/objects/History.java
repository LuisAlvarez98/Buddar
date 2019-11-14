package com.app.buddar.objects;

/**
 * History Item OBJ Class
 * Class by: Luis Felipe Alvarez Sanchez
 */
public class History {
    private String name;
    private String email;
    private String picture;
    private String bio;

    public History() {
    }

    public History(String name, String email, String picture, String bio) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

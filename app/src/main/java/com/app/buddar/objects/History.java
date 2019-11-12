package com.app.buddar.objects;

public class History {
    private String name;
    private String bio;

    public History(String name, String bio) {
        this.name = name;
        this.bio = bio;
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

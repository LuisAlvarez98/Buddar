package com.app.buddar.objects;

/**
 * History Item OBJ Class
 * Class by: Luis Felipe Alvarez Sanchez
 */
public class History {
    private String name;
    private String bio;
    private String date;

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

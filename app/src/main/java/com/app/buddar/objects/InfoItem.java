package com.app.buddar.objects;

/**
 * Info Item OBJ Class
 * Class by: Luis Felipe Alvarez Sanchez
 */
public class InfoItem {
    private String title;
    private String info;
    private String url;

    public InfoItem(String title, String info, String url) {
        this.title = title;
        this.info = info;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

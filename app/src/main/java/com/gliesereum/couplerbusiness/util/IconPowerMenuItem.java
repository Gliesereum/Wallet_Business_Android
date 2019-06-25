package com.gliesereum.couplerbusiness.util;

public class IconPowerMenuItem {

    private String id;
    private String title;
    private int ic_logo;

    public IconPowerMenuItem(String title, String id, int ic_logo) {
        this.title = title;
        this.id = id;
        this.ic_logo = ic_logo;
    }

    public int getIc_logo() {
        return ic_logo;
    }

    public void setIc_logo(int ic_logo) {
        this.ic_logo = ic_logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

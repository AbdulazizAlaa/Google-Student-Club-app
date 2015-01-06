package com.bluewasp.themonobly.Models;

/**
 * Created by Lenovo on 1/6/2015.
 */
public class SideMenuItemData {

    private String title;
    private int icon;

    public SideMenuItemData(String title, int icon) {
        super();
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}

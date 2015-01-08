package com.bluewasp.themonobly.Models;

import android.graphics.Bitmap;

/**
 * Created by Lenovo on 1/8/2015.
 */
public class ProfileListItemData {

    private String description;
    private Bitmap image;

    public ProfileListItemData(String description, Bitmap image) {
        this.description = description;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

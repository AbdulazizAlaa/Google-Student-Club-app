package com.bluewasp.themonobly.Models;

import android.graphics.Bitmap;

/**
 * Created by Lenovo on 2/12/2015.
 */
public class CommunityCardsData {

    private Bitmap profileImage;
    private String name, position, commity, level;


    public CommunityCardsData(Bitmap profileImage, String name, String position, String commity, String level) {
        this.profileImage = profileImage;
        this.name = name;
        this.position = position;
        this.commity = commity;
        this.level = level;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getCommity() {
        return commity;
    }

    public String getLevel() {
        return level;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCommity(String commity) {
        this.commity = commity;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

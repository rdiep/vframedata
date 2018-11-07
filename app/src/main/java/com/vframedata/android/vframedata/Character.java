package com.vframedata.android.vframedata;

public class Character {

    private String Name;
    private int Thumbnail;

    public Character(){

    }

    public Character(String name, int thumbnail) {
        Name = name;
        Thumbnail = thumbnail;
    }

    public String getName() {
        return Name;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}

package com.example.flotest;

public class MusicList {
    //String imgURL = null;
    int imgURL = 0;
    String title = "제목없음";
    String artist = "가수없음";

    MusicList(int imgURL, String title, String artist){
        this.imgURL = imgURL;
        this.title = title;
        this.artist = artist;
    }

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int get_imgURL(){
        return imgURL;
    }

    public String get_title(){
        return title;
    }

    public String get_artist(){
        return artist;
    }
}

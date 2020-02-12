package com.houssam.trollat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private String imagelink;
    private String name;
    private String body;
    private Date date;
    private int nblike;
    private ArrayList<Replay> replays = new ArrayList<>();
    public Comment(){

    }
    public Comment(String imagelink, String name, String body, Date date, int nblike, ArrayList<Replay> replays) {
        this.imagelink = imagelink;
        this.name = name;
        this.body = body;
        this.date = date;
        this.nblike = nblike;
        this.replays = replays;
    }

    public void addAllReplay(List<Replay> replay){
        replays.addAll(replay);
    }


    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNblike() {
        return nblike;
    }

    public void setNblike(int nblike) {
        this.nblike = nblike;
    }

    public ArrayList<Replay> getReplays() {
        return replays;
    }

    public void setReplays(ArrayList<Replay> replays) {
        this.replays = replays;
    }
}

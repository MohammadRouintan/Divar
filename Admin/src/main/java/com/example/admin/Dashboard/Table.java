package com.example.admin.Dashboard;

public class Table {
    private String time ,city ,username ,mainBranch ,title;
    private int postID;

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getPostID() {
        return postID;
    }

    public String getTime() {
        return time;
    }

    public String getCity() {
        return city;
    }

    public String getUsername() {
        return username;
    }

    public String getMainBranch() {
        return mainBranch;
    }

    public String getTitle() {
        return title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMainBranch(String mainBranch) {
        this.mainBranch = mainBranch;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Table(String time, String city, String username, String mainBranch, String title ,int postID) {
        this.time = time;
        this.city = city;
        this.username = username;
        this.mainBranch = mainBranch;
        this.title = title;
        this.postID = postID;
    }
}

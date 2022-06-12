package com.example.server.Database.Posts;

import org.bson.Document;

public interface PostFunctions {

    public void addToDatabase();
    public void deleteFromDatabase();
    public void updateFromDatabase();
    public Document findFromDatabase();
    public String getPost();
    public boolean isPostExists();

}

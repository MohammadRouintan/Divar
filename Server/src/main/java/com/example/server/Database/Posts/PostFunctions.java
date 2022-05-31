package com.example.server.Database.Posts;

import org.bson.json.JsonObject;

public interface PostFunctions {

    public void addToDatabase();
    public void deleteFromDatabase();
    public void updateFromDatabase();
    public void findFromDatabase();
    public String getPost();

}

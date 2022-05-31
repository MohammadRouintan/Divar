package com.example.server.Database.Posts;

import com.mongodb.client.FindIterable;
import org.bson.Document;

public interface PostFunctions {

    public void addToDatabase();
    public void deleteFromDatabase();
    public void updateFromDatabase();
    public Document findFromDatabase();

}

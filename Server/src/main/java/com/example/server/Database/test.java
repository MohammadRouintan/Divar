package com.example.server.Database;

import com.example.server.Database.Posts.Post;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        System.out.println(Database.lastImageIdOfPosts());
    }
}

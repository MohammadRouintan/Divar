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
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(dateTimeFormatter.format(localDateTime));
        //Timestamp timestamp = new Timestamp(date.);
        ArrayList<String> img = new ArrayList<>();
        img.add("1");
        img.add("2");
//        Post post = new Post(Database.lastPostId() + 1, "bio", "salam", img, "aaaa", "123",
//                "tehran", "adf", "09111111", true, true, true, true, null, null, null, null, "vehicles", "wer");
//        Database.addPost(post);
//        ArrayList<Object> key = new ArrayList<>();
//        key.add("num");
//        key.add("bio");
//        ArrayList<Object> value = new ArrayList<>();
//        value.add("11111");
//        value.add("111");
//        Document d = new Document();
//        d.append("updateKeys", key);
//        d.append("updateValues", value);
//        Post post = new Post(new Document("postId", 5), new Document("$set", d));
//
//        Post post = new Post(new Document("postId", 4));
//        Database.deletePost(post);
        System.out.println(Database.lastUserImageId());
    }
}

package com.example.server.Database;

import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.geometry.Pos;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;


public class Database {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void connectToDatabase() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("Divar");
    }

    public static void addPost(Post post) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        collection.insertOne(post.getDocument());
        disconnect();
    }

    public static void addUsers(Users users) {
        connectToDatabase();
        collection = database.getCollection("Users");
        collection.insertOne(users.getDocument());
        disconnect();
    }

    public static void updatePost(Post post) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        collection.updateOne(post.getFilter(), post.getUpdate());
        disconnect();
    }

    public static void updateUsers(Users users) {
        connectToDatabase();
        collection = database.getCollection("Users");
        collection.updateOne(users.getFilter(), users.getUpdate());
        disconnect();
    }

    public static void findPost(Document filter) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        collection.find(filter);
        disconnect();
    }

    public static void findUser(Document filter) {
        connectToDatabase();
        collection = database.getCollection("Users");
        collection.find(filter);
        disconnect();
    }

    public static int lastPostId() {
        int lastId = 0;
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            lastId = obj.getInt("postId");
        }
        return lastId;
    }

    public static String lastImageId() {
        String lastId = "";
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            JSONArray jsonArray = obj.getJSONArray("imageName");
            lastId = String.valueOf(jsonArray.getInt(jsonArray.length() - 1));
        }
        return lastId;
    }

    public static void disconnect(){
        mongoClient.close();
    }

}

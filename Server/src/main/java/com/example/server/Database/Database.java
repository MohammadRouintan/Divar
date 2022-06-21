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

import java.util.ArrayList;


public class Database {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void connectToDatabase() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("Divar");
    }

    public synchronized static void addPost(Post post) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        collection.insertOne(post.getDocument());
        disconnect();
    }

    public synchronized static void addUsers(Users users) {
        connectToDatabase();
        collection = database.getCollection("Users");
        collection.insertOne(users.getDocument());
        disconnect();
    }

    public synchronized static void updatePost(Post post) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        JSONObject json = new JSONObject(post.getUpdateDocument());
        Object newDocument = json.get("$set");
        JSONObject object = new JSONObject(newDocument.toString());
        JSONArray updateKeys = object.getJSONArray("updateKeys");
        JSONArray updateValues = object.getJSONArray("updateValues");
        for (int i=0; i < updateKeys.length(); i++) {
            collection.updateOne(post.getFilterDocument() ,new Document("$set" ,new Document(updateKeys.getString(i) ,updateValues.get(i))));
        }
        disconnect();
    }

    public synchronized static void updateUsers(Users users) {
        connectToDatabase();
        collection = database.getCollection("Users");
        JSONObject json = new JSONObject(users.getUpdateDocument());
        Object newDocument = json.get("$set");
        JSONObject object = new JSONObject(newDocument.toString());
        JSONArray updateKeys = object.getJSONArray("updateKeys");
        JSONArray updateValues = object.getJSONArray("updateValues");
        for (int i=0; i < updateKeys.length(); i++) {
            collection.updateOne(users.getFilterDocument() ,new Document("$set" ,new Document(updateKeys.getString(i) ,updateValues.get(i))));
        }
        disconnect();
    }

    public synchronized static Document findPost(Document filter) {
        connectToDatabase();
        Document document = new Document("", "");
        collection = database.getCollection("Posts");
        if (collection.find(filter).cursor().hasNext()) {
            document = collection.find(filter).cursor().next();
        }
        disconnect();
        return document;
    }

    public synchronized static Document findUser(Document filter) {
        connectToDatabase();
        Document document = new Document("", "");
        collection = database.getCollection("Users");
        if (collection.find(filter).cursor().hasNext()) {
            document = collection.find(filter).cursor().next();
        }
        disconnect();
        return document;
    }

    public synchronized static void deletePost(Post post) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        if (collection.find(post.getFilterDocument()).cursor().hasNext()) {
            collection.deleteOne(post.getFilterDocument());
        }
        disconnect();
    }

    public synchronized static void deleteUser(Users users) {
        connectToDatabase();
        collection = database.getCollection("Users");
        if (collection.find(users.getFilterDocument()).cursor().hasNext()) {
            collection.deleteOne(users.getFilterDocument());
        }
        disconnect();
    }

    public static int lastPostId() {
        connectToDatabase();
        collection = database.getCollection("Posts");
        int lastId = 0;
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            lastId = obj.getInt("postId");
        }
        disconnect();
        return lastId;
    }

    public static String lastImageId() {
        connectToDatabase();
        collection = database.getCollection("Posts");
        String lastId = "";
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            JSONArray jsonArray = obj.getJSONArray("imageName");
            lastId = String.valueOf(jsonArray.getInt(jsonArray.length() - 1));
        }
        disconnect();
        return lastId;
    }

    public static ArrayList<String> getPosts(int number, String branchMain) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        int temp = number;
        ArrayList<String> posts = new ArrayList<>();
        Document document = new Document("branchMain", branchMain);
        for (int i = lastPostId(); i > 0; i--) {
            if(temp == 0) {
                break;
            }
            document.append("postId", i);
            if (collection.find(document).cursor().hasNext()) {
                posts.add(collection.find(document).cursor().next().toJson());
                temp--;
            }
            document.remove("postId", i);
        }
        disconnect();
        return posts;
    }

    public static String getPost(Document filter) {
        return findPost(filter).toJson();
    }

    public static String getUser(Document filter) {
        return findUser(filter).toJson();
    }

    public ArrayList<String> lastSeenPost(Document filter) {
        String user = getUser(filter);
        JSONObject jsonObject = new JSONObject(user);
        JSONArray jsonArray = jsonObject.getJSONArray("lastSeenPost");
        ArrayList<String> lastSeen = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            lastSeen.add(getPost(new Document("postId", jsonArray.get(i))));
        }
        return lastSeen;
    }

    public static void disconnect(){
        mongoClient.close();
    }

    public static boolean isUserExits(Users users){
        connectToDatabase();
        return collection.find(users.getFilterDocument()).cursor().hasNext();
    }

    public static boolean isPostExits(Post post){
        connectToDatabase();
        return collection.find(post.getFilterDocument()).cursor().hasNext();
    }
}
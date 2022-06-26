package com.example.server.Database;

import com.example.server.Database.Messages.Messages;
import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Database {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    public static int imageID;
    public static int profileImageID;

    public static int lastImageID(){
        imageID ++;
        return imageID;
    }

    public static int lastProfileImageID(){
        profileImageID ++;
        return profileImageID;
    }

    public static void connect() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("Divar");
    }

    public synchronized static void addPost(Post post) {
        connect();
        collection = database.getCollection("Posts");
        collection.insertOne(post.getDocument());
        disconnect();
        Users users = new Users(post.getDocument().getString("phoneNumber"));
        updateUserArrays(users, "userPosts");
    }

    public synchronized static void addUser(Users users) {
        connect();
        collection = database.getCollection("Users");
        if (!collection.find(users.getDocument()).cursor().hasNext()) {
            collection.insertOne(users.getDocument());
        }
        disconnect();
    }

    public synchronized static void updatePost(Post post ,String key ,Object value) {
        connect();
        collection = database.getCollection("Posts");
        collection.updateOne(post.getFilterDocument() ,new Document("$set" ,new Document(key ,value)));
        disconnect();
    }

    public synchronized static void updateUser(Users users ,String key ,Object value) {
        connect();
        collection = database.getCollection("Users");
        collection.updateOne(users.getDocument() ,new Document("$set" ,new Document(key ,value)));
        disconnect();
    }

    public synchronized static Document findPost(Document filter) {
        connect();
        Document document = new Document("", "");
        collection = database.getCollection("Posts");
        if (collection.find(filter).cursor().hasNext()) {
            document = collection.find(filter).cursor().next();
        }
        disconnect();
        return document;
    }

    public static String getPost(Document filter) {
        return findPost(filter).toJson();
    }

    public synchronized static Document findUser(Document filter) {
        connect();
        Document document = new Document("", "");
        collection = database.getCollection("Users");
        if (collection.find(filter).cursor().hasNext()) {
            document = collection.find(filter).cursor().next();
        }
        disconnect();
        return document;
    }

    public static String getUser(Document filter) {
        return findUser(filter).toJson();
    }

    public synchronized static void deletePost(Post post) {
        connect();
        collection = database.getCollection("Posts");
        if (collection.find(post.getFilterDocument()).cursor().hasNext()) {
            collection.deleteOne(post.getFilterDocument());
        }
        disconnect();
    }

    public synchronized static void deleteUser(Users users) {
        connect();
        collection = database.getCollection("Users");
        if (collection.find(users.getDocument()).cursor().hasNext()) {
            collection.deleteOne(users.getDocument());
        }
        disconnect();
    }

    public static boolean isPostExits(Post post){
        connect();
        collection = database.getCollection("Posts");
        boolean flag = collection.find(post.getFilterDocument()).cursor().hasNext();
        disconnect();
        return flag;
    }

    public static boolean isUserExits(Users users){
        connect();
        collection = database.getCollection("Users");
        boolean flag = collection.find(users.getDocument()).cursor().hasNext();
        disconnect();
        return flag;
    }

    public synchronized static void updateUserArrays(Users users, String arrayName) {
        JSONObject user = new JSONObject(getUser(users.getDocument()));
        ArrayList<String> Posts;
        if (user.has(arrayName)) {
            Posts = getStringArray(user.getJSONArray(arrayName));
            Posts.add(String.valueOf(lastPostId()));
        } else {
            Posts = new ArrayList<>();
            Posts.add(String.valueOf(lastPostId()));
        }
        updateUser(users, arrayName, Posts);
    }

    public static int lastPostId() {
        connect();
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

    public static int lastImageIdOfPosts() {
        int lastPostId = lastPostId();
        connect();
        collection = database.getCollection("Posts");
        int lastId = 1;
        for (int i = lastPostId; i >= 0; i--) {
            String jsonString = collection.find(new Document("postId", i)).cursor().next().toJson();
            JSONObject post = new JSONObject(jsonString);
            JSONArray imageName = post.getJSONArray("imageName");
            int temp = imageName.getInt(imageName.length() - 1);
            if (temp > lastId) {
                lastId = temp;
                break;
            }
        }
        disconnect();
        return lastId;
    }

    public static ArrayList<String> getNotAcceptedPosts() {
        connect();
        collection = database.getCollection("Posts");
        ArrayList<String> notAccepted = new ArrayList<>();
        if(collection.find(new Document("accept", false)).cursor().hasNext()) {
            for (Document document : collection.find(new Document("accept", false))) {
                notAccepted.add(document.toJson());
            }
        }
        disconnect();
        return notAccepted;
    }

    public static ArrayList<String> getPosts(int number, String key, Object value, int index) {
        connect();
        collection = database.getCollection("Posts");
        ArrayList<String> posts = new ArrayList<>();
        if (collection.find(new Document(key, value)).cursor().hasNext()) {
            FindIterable<Document> post = collection.find(new Document(key, value)).sort(new Document("postId", -1)).skip(index * number);
            for (Document document : post) {
                posts.add(document.toJson());
            }
        }
        disconnect();
        return posts;
    }

    public static ArrayList<String> lastSeenPost(Document filter) {
        String user = getUser(filter);
        JSONObject jsonObject = new JSONObject(user);
        ArrayList<String> jsonArray = getStringArray(jsonObject.getJSONArray("lastSeenPost"));
        ArrayList<String> lastSeen = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            lastSeen.add(getPost(new Document("postId", Integer.parseInt(jsonArray.get(i)))));
        }
        return lastSeen;
    }

    public static Users getUserAsDoc(Document filter) {
        return new Users(filter).setValues(findUser(filter));
    }



    public static void disconnect(){
        mongoClient.close();
    }

    public static ArrayList<String> getMarkedPosts(int size, Users user){
        String temp = getUser(user.getDocument());
        JSONObject object = new JSONObject(temp);
        ArrayList<String> jsonArray = getStringArray(object.getJSONArray("bookmarkPost"));
        ArrayList<String> markedPost = new ArrayList<>();
        for (int i = user.getNumberForMarkedPost() * size; i < (size * user.getNumberForMarkedPost()) + size; i++) {
            if(i < jsonArray.size()) {
                markedPost.add(getPost(new Document("postId", Integer.parseInt(jsonArray.get(i)))));
            }
        }
        user.setNumberForMarkedPost(user.getNumberForMarkedPost() + 1);
        return markedPost;
    }


    public static ArrayList<String> getUsersPosts(int size, Users user){
        String temp = getUser(user.getDocument());
        JSONObject object = new JSONObject(temp);
        ArrayList<String> jsonArray = getStringArray(object.getJSONArray("userPosts"));
        ArrayList<String> usersPost = new ArrayList<>();
        for (int i = user.getNumberForUsersPost() * size; i < (size * user.getNumberForUsersPost()) + size; i++) {
            if(i < jsonArray.size()) {
                usersPost.add(getPost(new Document("postId", Integer.parseInt(jsonArray.get(i)))));
            }
        }
        user.setNumberForUsersPost(user.getNumberForUsersPost() + 1);
        return usersPost;
    }

    public static void addMessage(Messages messages){
        connect();
        collection = database.getCollection("Chats");
        collection.insertOne(messages.getDocument());
        disconnect();
    }

    public static void updateMessage(Messages messages ,String key ,Object value){
        connect();
        collection = database.getCollection("Chats");
        collection.updateOne(messages.getFilterDocument() ,new Document("$set" ,new Document(key ,value)));
        disconnect();
    }

    public synchronized static void deleteMessage(Messages messages) {
        connect();
        collection = database.getCollection("Chats");
        if (collection.find(messages.getFilterDocument()).cursor().hasNext()) {
            collection.deleteOne(messages.getFilterDocument());
        }
        disconnect();
    }

    public synchronized static Document findMessage(Document filter) {
        connect();
        Document document = new Document("", "");
        collection = database.getCollection("Chats");
        if (collection.find(filter).cursor().hasNext()) {
            document = collection.find(filter).cursor().next();
        }
        disconnect();
        return document;
    }

    public synchronized static ArrayList<String> findPartner(String phoneNumber) {
        connect();
        collection = database.getCollection("Chats");
        ArrayList<String> partner = new ArrayList<>();
        if (collection.find(new Document("user1", phoneNumber)).cursor().hasNext()) {
            for (Document document : collection.find(new Document("user1", phoneNumber))) {
                partner.add(document.getString("user2"));
            }
        } else if (collection.find(new Document("user2", phoneNumber)).cursor().hasNext()) {
            for (Document document : collection.find(new Document("user2", phoneNumber))) {
                partner.add(document.getString("user1"));
            }
        }
        disconnect();
        return partner;
    }

    public synchronized static String findChat(String phoneNumber1, String phoneNumber2) {
        connect();
        collection = database.getCollection("Chats");
        String chat = "0";
        FindIterable<Document> chat1 = collection.find(new Document("user1", phoneNumber1).append("user2", phoneNumber2));
        FindIterable<Document> chat2 = collection.find(new Document("user1", phoneNumber2).append("user2", phoneNumber1));

        if (chat1.cursor().hasNext()) {
            chat = chat1.cursor().next().toJson();
        } else if (chat2.cursor().hasNext()) {
            chat = chat2.cursor().next().toJson();
        }
        disconnect();
        return chat;
    }

    public static String getMessage(Document filter) {
        return findMessage(filter).toJson();
    }

    public static int numberOfPostsOfUser(Users users) {
        connect();
        int number = 0;
        String user = getUser(users.getDocument());
        JSONObject object = new JSONObject(user);
        JSONArray jsonArray = object.getJSONArray("userPosts");
        number = jsonArray.length();
        disconnect();
        return number;
    }

    public static int lastUserImageId() {
        connect();
        collection = database.getCollection("Users");
        int lastId = 1;
        FindIterable<Document> profile = collection.find(new Document("profileImageName", 1)).sort(new Document("profileNameImage", -1));
        if (profile.cursor().hasNext()) {
            for (Document document : profile) {
                int temp = document.getInteger("profileNameImage");
                if (temp > lastId) {
                    lastId = temp;
                }
            }
        }
        disconnect();
        return lastId;
    }

    public static ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }

    public static ArrayList<String> search(int size, String search) {
        connect();
        int temp = size;
        collection = database.getCollection("Posts");
        ArrayList<String> posts = new ArrayList<>();
        if (collection.find().cursor().hasNext()) {
            for (Document document : collection.find()) {
                if (temp == 0) {
                    break;
                }
                String title = document.getString("title");
                if (title.contains(search)) {
                    posts.add(document.toJson());
                    temp--;
                }
            }
        }
        disconnect();
        return posts;
    }

}
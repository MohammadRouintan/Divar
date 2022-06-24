package com.example.server.Database;

import com.example.server.Database.Messages.Messages;
import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
import com.mongodb.client.*;
import javafx.geometry.Pos;
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

    public static void connectToDatabase() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("Divar");
    }

    public synchronized static void addPost(Post post) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        collection.insertOne(post.getDocument());
        disconnect();
        String phoneNumber = post.getDocument().getString("phoneNumber");
        Users users = new Users(phoneNumber);
        updateUserArrays(users, "userPosts");
    }

    public synchronized static void updateUserArrays(Users users, String arrayName) {
        JSONObject user = new JSONObject(findUser(users.getFilterDocument()).toJson());
        ArrayList<String> Posts;
        if (user.has(arrayName)) {
            JSONArray userPosts = user.getJSONArray(arrayName);
            Posts = getStringArray(userPosts);
            Posts.add(String.valueOf(lastPostId() + 1));
        } else {
            Posts = new ArrayList<>();
            Posts.add(String.valueOf(lastPostId() + 1));
        }
        updateUsers(users, arrayName, Posts);
    }

    public synchronized static void addUsers(Users users) {
        connectToDatabase();
        collection = database.getCollection("Users");
        if (!collection.find(users.getDocument()).cursor().hasNext()) {
            collection.insertOne(users.getDocument());
        }
        disconnect();
    }

    public synchronized static void updatePost(Post post ,String key ,Object value) {
        connectToDatabase();
        collection = database.getCollection("Posts");
        collection.updateOne(post.getFilterDocument() ,new Document("$set" ,new Document(key ,value)));
        disconnect();
    }

    public synchronized static void updateUsers(Users users ,String key ,Object value) {
        connectToDatabase();
        collection = database.getCollection("Users");
        collection.updateOne(users.getDocument() ,new Document("$set" ,new Document(key ,value)));

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


    public static String lastImageIDFromDatabase() {
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

    public static ArrayList<String> getNotAcceptedPosts() {
        connectToDatabase();
        collection = database.getCollection("Posts");
        ArrayList<String> notAccepted = new ArrayList<>();
        if(collection.find(new Document("accept", false)).cursor().hasNext()) {
            MongoCursor<Document> cursor = collection.find(new Document("accept", false)).iterator();
            while (cursor.hasNext()) {
                notAccepted.add(cursor.next().toJson());
            }
        }
        disconnect();
        return notAccepted;
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

    public static Users getUserAsDoc(Document filter) {
        return new Users(filter).setValues(findUser(filter));
    }

    public static ArrayList<String> lastSeenPost(Document filter) {
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

    private static int numberForMarkedPost = 0;


    public static ArrayList<String> getMarkedPosts(int size, Document filter){

        String user = getUser(filter);
        JSONObject object = new JSONObject(user);
        JSONArray jsonArray = object.getJSONArray("bookmarkPost");
        ArrayList<String> markedPost = new ArrayList<>();
        for (int i = numberForMarkedPost * size; i < (size * numberForMarkedPost) + size; i++) {
            if(i < jsonArray.length()) {
                markedPost.add(getPost(new Document("postId", jsonArray.get(i))));
            }
        }
        numberForMarkedPost++;
        return markedPost;
    }

    private static int numberForUsersPost = 0;

    public static ArrayList<String> getUsersPosts(int size, Document filter){

        String user = getUser(filter);
        JSONObject object = new JSONObject(user);
        ArrayList<String> userPosts = getStringArray(object.getJSONArray("userPosts"));
        ArrayList<String> usersPost = new ArrayList<>();
        for (int i = numberForUsersPost * size; i < (size * numberForUsersPost) + size; i++) {
            if(i < userPosts.size()) {
                usersPost.add(getPost(new Document("postId", Integer.parseInt(userPosts.get(i)))));
            }
        }
        numberForUsersPost++;
        return usersPost;
    }

    public static void addMessage(Messages messages){
        connectToDatabase();
        collection = database.getCollection("Messages");
        collection.insertOne(messages.getDocument());
        disconnect();
    }

    public static void updateMessage(Messages messages){
        connectToDatabase();
        collection = database.getCollection("Messages");
        JSONObject json = new JSONObject(messages.getUpdateDocument());
        Object newDocument = json.get("$set");
        JSONObject object = new JSONObject(newDocument.toString());
        JSONArray updateKeys = object.getJSONArray("updateMessageKeys");
        JSONArray updateValues = object.getJSONArray("updateMessageValues");
        for (int i=0; i < updateKeys.length(); i++) {
            collection.updateOne(messages.getFilterDocument() ,new Document("$set" ,new Document(updateKeys.getString(i) ,updateValues.get(i))));
        }
        disconnect();
    }

    public synchronized static void deleteMessage(Messages messages) {
        connectToDatabase();
        collection = database.getCollection("Messages");
        if (collection.find(messages.getFilterDocument()).cursor().hasNext()) {
            collection.deleteOne(messages.getFilterDocument());
        }
        disconnect();
    }

    public synchronized static Document findMessage(Document filter) {
        connectToDatabase();
        Document document = new Document("", "");
        collection = database.getCollection("Messages");
        if (collection.find(filter).cursor().hasNext()) {
            document = collection.find(filter).cursor().next();
        }
        disconnect();
        return document;
    }

    public static String getMessage(Document filter) {
        return findMessage(filter).toJson();
    }

    public static String numberOfPostsOfUser(Users users) {
        connectToDatabase();
        String number = "";
        String user = getUser(users.getFilterDocument());
        JSONObject object = new JSONObject(user);
        JSONArray jsonArray = object.getJSONArray("usersPost");
        number = String.valueOf(jsonArray.length());
        disconnect();
        return number;
    }
    public static String lastUserImageId() {
        connectToDatabase();
        collection = database.getCollection("Users");
        String lastId = "1";
        if (collection.find().cursor().hasNext()) {
            for (Document document : collection.find()) {
                String jsonString = document.toJson();
                JSONObject obj = new JSONObject(jsonString);
                if (obj.getInt("profileNameImage") > Integer.parseInt(lastId)) {
                    lastId = String.valueOf(obj.getInt("profileNameImage"));
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

}
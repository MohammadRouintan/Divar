package com.example.server.Database;

import com.example.server.Database.Messages.Messages;
import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


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
        updateUserArrays(users, "userPosts", lastPostId());
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

    public synchronized static void updateUser(Users users, String key, Object value, String typeOfUpdate) {
        connect();
        collection = database.getCollection("Users");
        if (typeOfUpdate.equals("set")) {
            collection.updateOne(users.getDocument() ,new Document("$set" ,new Document(key ,value)));
        } else if (typeOfUpdate.equals("unset")) {
            collection.updateOne(users.getDocument() ,new Document("$unset" ,new Document(key ,value)));
        }
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

    public synchronized static void updateUserArrays(Users users, String arrayName, int number) {
        JSONObject user = new JSONObject(getUser(users.getDocument()));
        ArrayList<Integer> Posts;
        if (user.has(arrayName)) {
            Posts = getIntegerArray(user.getJSONArray(arrayName));
            if (arrayName.equals("lastSeenPost") && Posts.size() == 16) {
                Posts.remove(0);
            }

            if (arrayName.equals("lastSeenPost")) {
                if (Posts.contains(number)) {
                    Posts.remove(Posts.indexOf(number));
                }
                Posts.add(number);
            } else if (arrayName.equals("bookmarkPost") && Posts.contains(number)) {
                Posts.remove(Posts.indexOf(number));
            } else {
                Posts.add(number);
            }
        } else {
            Posts = new ArrayList<>();
            Posts.add(number);
        }

        if (Posts.size() == 0) {
            updateUser(users, arrayName, true, "unset");
        } else {
            updateUser(users, arrayName, Posts, "set");
        }
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
        connect();
        collection = database.getCollection("Posts");
        int lastId = 0;
        List<Document> documents = new ArrayList<>();
        documents.add(new Document("$project", new Document("imageName", 1)));
        documents.add(new Document("$sort", new Document("imageName", -1)));
        if (collection.aggregate(documents).cursor().hasNext()) {
            String jsonString = collection.aggregate(documents).cursor().next().toJson();
            JSONObject post = new JSONObject(jsonString);
            JSONArray imageName = post.getJSONArray("imageName");
            int temp = imageName.getInt(imageName.length() - 1);
            if (temp > lastId) {
                lastId = temp;
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


    public static ArrayList<String> getPosts(int size, int index, Document filter) {
        connect();
        collection = database.getCollection("Posts");
        ArrayList<String> posts = new ArrayList<>();
        FindIterable<Document> post = collection.find(filter).sort(new Document("postId", -1));
        if (post.cursor().hasNext()) {
            for (Document document : post.skip(index * size).limit(size)) {
                posts.add(document.toJson());
            }
        }
        disconnect();
        return posts;
    }

    public static ArrayList<String> search(int size, int index, Document filter, String search) {
        connect();
        collection = database.getCollection("Posts");
        ArrayList<String> posts = new ArrayList<>();
        FindIterable<Document> post = collection.find(filter).sort(new Document("postId", -1));
        if (post.cursor().hasNext()) {
            for (Document document : post.skip(index * size).limit(size)) {
                String title = document.getString("title");
                if (title.contains(search)) {
                    posts.add(document.toJson());
                }
            }
        }
        disconnect();
        return posts;
    }

    public static ArrayList<String> priceFilter(long priceFrom, long priceTo, ArrayList<String> posts) {
        ArrayList<String> newPost = new ArrayList<>();
        JSONObject jsonObject;
        for (int i = 0; i < posts.size(); i++) {
            jsonObject = new JSONObject(posts.get(i));
            if (jsonObject.has("price") && jsonObject.getLong("price") >= priceFrom && jsonObject.getLong("price") <= priceTo) {
                newPost.add(posts.get(i));
            }
        }
        return newPost;
    }

    public static ArrayList<String> lastSeenPost(int size, int index, Users users) {
        String user = getUser(users.getDocument());
        JSONObject jsonObject = new JSONObject(user);
        ArrayList<Integer> jsonArray = getIntegerArray(jsonObject.getJSONArray("lastSeenPost"));
        ArrayList<String> lastSeen = new ArrayList<>();
        for (int i = jsonArray.size() - 1 - (index * size); i > Math.max(jsonArray.size() - 1 - (index + 1) * size, -1); i--) {
            lastSeen.add(getPost(new Document("postId", jsonArray.get(i))));
        }
        return lastSeen;
    }

    public static Users getUserAsDoc(Document filter) {
        return new Users(filter).setValues(findUser(filter));
    }



    public static void disconnect(){
        mongoClient.close();
    }

    public static ArrayList<String> getMarkedPosts(int size, int index, Users user){
        String temp = getUser(user.getDocument());
        JSONObject object = new JSONObject(temp);
        ArrayList<Integer> jsonArray = getIntegerArray(object.getJSONArray("bookmarkPost"));
        ArrayList<String> bookmarkPost = new ArrayList<>();
        for (int i = jsonArray.size() - 1 - (index * size); i > Math.max(jsonArray.size() - 1 - (index + 1) * size, -1); i--) {
            bookmarkPost.add(getPost(new Document("postId", jsonArray.get(i))));
        }
        return bookmarkPost;
    }


    public static ArrayList<String> getUsersPosts(int size, int index, Users user){
        String temp = getUser(user.getDocument());
        JSONObject object = new JSONObject(temp);
        ArrayList<Integer> jsonArray = getIntegerArray(object.getJSONArray("userPosts"));
        ArrayList<String> usersPost = new ArrayList<>();
        for (int i = jsonArray.size() - 1 - (index * size); i > Math.max(jsonArray.size() - 1 - (index + 1) * size, -1); i--) {
            usersPost.add(getPost(new Document("postId", jsonArray.get(i))));
        }
        return usersPost;
    }

    public static int getSizeOfArrays(String name, Users users) {
        String temp = getUser(users.getDocument());
        JSONObject object = new JSONObject(temp);
        if (object.has(name)) {
            ArrayList<Integer> jsonArray = getIntegerArray(object.getJSONArray(name));
            return jsonArray.size();
        }
        return 0;
    }

    public static void addMessage(Messages messages){
        connect();
        collection = database.getCollection("Chats");
        collection.insertOne(messages.getDocument());
        disconnect();
    }

    public synchronized static void updateMessage(Messages messages ,String key ,Object value){
        connect();
        collection = database.getCollection("Chats");
        collection.updateOne(messages.getFilterDocument() ,new Document("$set" ,new Document(key ,value)));
        disconnect();
    }

    public synchronized static void sendMessage(Messages messages, String message) {
        String oldMessage = findMessage(messages.getFilterDocument()).toJson();
        JSONObject jsonObject = new JSONObject(oldMessage);
        ArrayList<String> newMessages = getStringArray(jsonObject.getJSONArray("messages"));
        newMessages.add(message);
        updateMessage(messages, "messages", newMessages);
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
        int lastId = 0;
        List<Document> documents = new ArrayList<>();
        documents.add(new Document("$match", new Document("profileNameImage", 1)));
        documents.add(new Document("$sort", new Document("phoneNumber", -1)));
        if (collection.aggregate(documents).cursor().hasNext()) {
            lastId = collection.aggregate(documents).cursor().next().getInteger("profileNameImage");
        }
        disconnect();
        return lastId;
    }

    public static int getSizeOfPosts(Document filter) {
        connect();
        collection = database.getCollection("Posts");
        int counter = 0;
        FindIterable<Document> posts = collection.find(filter);
        if (posts.cursor().hasNext()) {
            for (Document post : posts) {
                counter++;
            }
        }

        return counter;
    }

    public static String getUserCity(Users user) {
        String city = "Tehran";
        String usr = getUser(user.getDocument());
        JSONObject jsonObject = new JSONObject(usr);
        if (jsonObject.has("city")) {
            city = jsonObject.getString("city");
        }
        return city;
    }

    public static ArrayList<Integer> getIntegerArray(JSONArray JArray) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getInt(i));
        }
        return list;
    }

    public synchronized static ArrayList<Integer> getSizeOfNotSeenMessage(String phoneNumber, ArrayList<String> partners) {
        ArrayList<Integer> notSeen = new ArrayList<>();
        for (String partner : partners) {
            String chat = findChat(phoneNumber, partner);
            JSONObject jsonObject = new JSONObject(chat);
            int counter = 0;
            if (jsonObject.has("seen") && jsonObject.has("sender")) {
                JSONArray sender = jsonObject.getJSONArray("sender");
                JSONArray seen = jsonObject.getJSONArray("seen");
                for (int i = sender.length() - 1; i >= 0 ; i--) {
                    if (sender.getString(i).equals(partner) && !seen.getBoolean(i)) {
                        counter++;
                    } else {
                        break;
                    }
                }
            }
            notSeen.add(counter);
        }
        return notSeen;
    }

    public synchronized static ArrayList<String> getNameOfProfileImage(ArrayList<String> partners) {
        ArrayList<String> imageName = new ArrayList<>();
        for (String partner : partners) {
            String user = getUser(new Document("phoneNumber", partner));
            JSONObject jsonObject = new JSONObject(user);
            if (jsonObject.has("profileNameImage")) {
                imageName.add(jsonObject.getString("profileNameImage"));
            }
        }
        return imageName;
    }

    public synchronized static String getChatCount(String phoneNumber) {
        Document document = new Document();
        document.append("partner", findPartner(phoneNumber));
        document.append("size", getSizeOfNotSeenMessage(phoneNumber, findPartner(phoneNumber)));
        document.append("imageName", getNameOfProfileImage(findPartner(phoneNumber)));
        return document.toJson();
    }

    public synchronized static boolean isMessageExist(String phoneNumber1, String phoneNumber2) {
        connect();
        collection = database.getCollection("Chats");
        FindIterable<Document> chat1 = collection.find(new Document("user1", phoneNumber1).append("user2", phoneNumber2));
        FindIterable<Document> chat2 = collection.find(new Document("user1", phoneNumber2).append("user2", phoneNumber1));
        boolean flag = false;
        if (chat1.cursor().hasNext()) {
            flag = true;
        } else if (chat2.cursor().hasNext()) {
            flag = true;
        }
        disconnect();
        return flag;
    }

    public static ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }

}
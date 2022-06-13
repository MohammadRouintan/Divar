package com.example.server.Database.Posts;

import com.example.server.Database.Database;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;

public class Post extends Database {

    public Post(){
        super.collectionName = "Posts";
        super.connectToDatabase();
    }

    public void setBranchMain(String branchMain) {
        this.branchMain = branchMain;
    }

    public void setBranch1(String branch1) {
        this.branch1 = branch1;
    }

    public String getBranchMain() {
        return branchMain;
    }

    private String bio;
    private String title;
    private ArrayList<String> imageName;
    private String address;
    private String price;
    private String city;
    private String time;
    private String phoneNumber;
    private int postId;
    private int numberOfViews;
    private boolean accept;
    private boolean auction;
    private boolean inNardeban;
    private boolean exchange;
    private boolean agreement;
    private ArrayList<String> rowName;
    private ArrayList<String> rowValue;
    private ArrayList<String> columnName;
    private ArrayList<String> columnValue;
    private ArrayList<String> updateKeys;
    private ArrayList<Object> updateValues;

    public boolean isAuction() {
        return auction;
    }

    public boolean isExchange() {
        return exchange;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAuction(boolean auction) {
        this.auction = auction;
    }

    public void setExchange(boolean exchange) {
        this.exchange = exchange;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Document getDocument() {
        return document;
    }

    public int lastPostId() {
        int lastId = 0;
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            lastId = obj.getInt("postId");
        }
        return lastId;
    }

    public String  lastImageId() {
        String lastId = "";
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            JSONArray jsonArray = obj.getJSONArray("imageName");
            lastId = String.valueOf(jsonArray.getInt(jsonArray.length() - 1));
        }
        return lastId;
    }

    private String branchMain;
    private String branch1;

    public ArrayList<String> getPosts(int number, String branchMain) {
        int temp = number;
        ArrayList<String> posts = new ArrayList<>();
        Document document = new Document("branchMain", branchMain);
        for (int i = lastPostId(); i > 0; i--) {
            if(temp == 0) {
                break;
            }
            document.append("postId", i);
            if (super.collection.find(document).cursor().hasNext()) {
                posts.add(super.collection.find(document).cursor().next().toJson());
                temp--;
            }
            document.remove("postId", i);
        }
        return posts;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getImageName() {
        return imageName;
    }

    public void setImageName(ArrayList<String> imageName) {
        this.imageName = imageName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isInNardeban() {
        return inNardeban;
    }

    public void setInNardeban(boolean inNardeban) {
        this.inNardeban = inNardeban;
    }

    public ArrayList<String> getRowName() {
        return rowName;
    }

    public void setRowName(ArrayList<String> rowName) {
        this.rowName = rowName;
    }

    public ArrayList<String> getRowValue() {
        return rowValue;
    }

    public void setRowValue(ArrayList<String> rowValue) {
        this.rowValue = rowValue;
    }

    public ArrayList<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(ArrayList<String> columnName) {
        this.columnName = columnName;
    }

    public ArrayList<String> getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(ArrayList<String> columnValue) {
        this.columnValue = columnValue;
    }

    public ArrayList<String> getUpdateKeys() {
        return updateKeys;
    }

    public void setUpdateKeys(ArrayList<String> updateKeys) {
        this.updateKeys = updateKeys;
    }

    public ArrayList<Object> getUpdateValues() {
        return updateValues;
    }

    public void setUpdateValues(ArrayList<Object> updateValues) {
        this.updateValues = updateValues;
    }

    public void addToDatabase() {
        super.document.append("postId", lastPostId() + 1);
        super.document.append("title" , getTitle());
        super.document.append("branchMain" ,branchMain);
        super.document.append("branch1" ,branch1);
        super.document.append("phoneNumber" ,getPhoneNumber());
        super.document.append("bio" ,getBio());
        super.document.append("imageName" ,getImageName());
        super.document.append("city" ,getCity());
        super.document.append("address" ,getAddress());
        super.document.append("time" ,getTime());
        super.document.append("accept" ,isAccept());
        super.document.append("exchange" ,isExchange());
        super.document.append("agreement" ,isAgreement());
        super.document.append("auction" ,isAuction());
        if (getPrice() != null)
            document.append("price" ,getPrice());
        if (getRowName() != null)
            document.append("rowName" , getRowName());
        if (getRowValue() != null)
            document.append("rowValue" , getRowValue());
        if (getColumnName() != null)
            document.append("columnName" , getColumnName());
        if (getColumnValue() != null)
            document.append("columnValue" , getColumnValue());
        super.collection.insertOne(super.document);
        super.disConnect();
    }

    public void deleteFromDatabase() {
        super.collection.deleteOne(new Document("postId" ,getPostId()));
        disConnect();
    }

    public void updateFromDatabase() {
        for (int i = 0; i < getUpdateKeys().size(); i++) {
            super.collection.updateOne(new Document("postId", getPostId()),new Document("$set",new Document(getUpdateKeys().get(i), getUpdateValues().get(i))));
        }
        disConnect();
    }

    public Document findFromDatabase() {
        Document d = new Document("", "");
        if (super.collection.find(new Document("postId", getPostId())).cursor().hasNext()) {
            d = super.collection.find(new Document("postId", getPostId())).cursor().next();
        }
        disConnect();
        return d;
    }

    public String getPost() {
        return findFromDatabase().toJson();
    }

    public boolean isPostExists() {
        return super.collection.find(new Document("postId", getPostId())).cursor().hasNext();
    }

}

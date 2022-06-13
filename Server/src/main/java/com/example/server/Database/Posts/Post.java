package com.example.server.Database.Posts;

import com.example.server.Database.Database;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public void setBranch2(String branch2) {
        this.branch2 = branch2;
    }

    public String getBranchMain() {
        return branchMain;
    }

    public String getBranch1() {
        return branch1;
    }

    public String getBranch2() {
        return branch2;
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
    private ArrayList<String> dataArray1;
    private ArrayList<String> dataArray2;
    private ArrayList<String> dataArray3;
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

    public int lastImageId() {
        int lastId = 0;
        if (collection.find().sort(new Document("postId", -1)).limit(1).cursor().hasNext()) {
            String jsonString = collection.find().sort(new Document("postId", -1)).limit(1).cursor().next().toJson();
            JSONObject obj = new JSONObject(jsonString);
            JSONArray jsonArray = obj.getJSONArray("imageName");
            lastId =  jsonArray.getInt(jsonArray.length() - 1);
        }
        return lastId;
    }

    private String branchMain;
    private String branch1;
    private String branch2;

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

    public ArrayList<String> getDataArray1() {
        return dataArray1;
    }

    public void setDataArray1(ArrayList<String> dataArray1) {
        this.dataArray1 = dataArray1;
    }

    public ArrayList<String> getDataArray2() {
        return dataArray2;
    }

    public void setDataArray2(ArrayList<String> dataArray2) {
        this.dataArray2 = dataArray2;
    }

    public ArrayList<String> getDataArray3() {
        return dataArray3;
    }

    public void setDataArray3(ArrayList<String> dataArray3) {
        this.dataArray3 = dataArray3;
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
//        super.document.append("title" , getTitle());
//        super.document.append("branchMain" ,branchMain);
//        super.document.append("branch1" ,branch1);
//        super.document.append("branch2" ,branch2);
//        super.document.append("phoneNumber" ,getPhoneNumber());
        super.document.append("bio" ,getBio());
        super.document.append("imageName" ,getImageName());
//        super.document.append("city" ,getCity());
//        super.document.append("address" ,getAddress());
//        super.document.append("time" ,getTime());
//        super.document.append("numberOfViews" ,getNumberOfViews());
//        super.document.append("accept" ,isAccept());
//        super.document.append("exchange" ,isExchange());
//        super.document.append("inNardeban" ,isInNardeban());
//        super.document.append("agreement" ,isAgreement());
//        super.document.append("auction" ,isAuction());
        if (getPrice() != null)
            document.append("price" ,getPrice());
        if (getDataArray1() != null)
            document.append("dataArray1" ,getDataArray1());
        if (getDataArray2() != null)
            document.append("dataArray2" ,getDataArray2());
        if (getDataArray3() != null)
            document.append("dataArray3" ,getDataArray3());
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

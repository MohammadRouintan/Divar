package com.example.server.Database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;


public abstract class Database {
    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection<Document> collection;
    protected Document document = new Document();

    private String bio;
    private String title;
    private ArrayList<String> imageName;
    private String address;
    private String price;
    private String city;
    private String time;
    private int phoneNumber;
    private int postId;
    private int numberOfViews;
    private boolean accept;
    private boolean inNardeban;
    private ArrayList<String> dataArray1;
    private ArrayList<String> dataArray2;
    private ArrayList<String> dataArray3;
    private ArrayList<String> updateKeys;
    private ArrayList<Object> updateValues;

    public void setCollection(String collectionName){
        collection = database.getCollection(collectionName);
    }

    public Document getDocument() {
        return document;
    }

    public void connectToDatabase() {
        mongoClient = new MongoClient("localhost",27017);
        database = mongoClient.getDatabase("Divar");
    }

    public void disConnect(){
        mongoClient.close();
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
}

package com.example.server.Database.Users;

import com.example.server.Database.Database;

import org.bson.Document;

import java.util.ArrayList;

public class Users extends Database {

    private Document document;
    private Document filterDocument;
    private Document updateDocument;

    public Users(Document filterDocument) {
        this.filterDocument = filterDocument;
    }

//    public Users(Document filterDocument ,Document updateDocument) {
//        this.filterDocument = filterDocument;
//        this.updateDocument = updateDocument;
//
//    }

    public Users(String phoneNumber) {
        document = new Document();
        document.append("phoneNumber", phoneNumber);
    }

    public Document getDocument() {
        return document;
    }

    public Document getFilterDocument() {
        return filterDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public ArrayList<String> getBookMarkedPost() {
        return bookMarkedPost;
    }

    public ArrayList<String> getUsersPosts() {
        return usersPosts;
    }

    public ArrayList<String> getLastSeenPost() {
        return lastSeenPost;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBookMarkedPost(ArrayList<String> bookMarkedPost) {
        this.bookMarkedPost = bookMarkedPost;
    }

    public void setUsersPosts(ArrayList<String> usersPosts) {
        this.usersPosts = usersPosts;
    }

    public void setLastSeenPost(ArrayList<String> lastSeenPost) {
        this.lastSeenPost = lastSeenPost;
    }

    public Document getUpdateDocument() {
        return updateDocument;
    }

    public Users(String phoneNumber, String city, String lastName, String firstName, ArrayList<String> bookMarkedPost, ArrayList<String> usersPosts, ArrayList<String> lastSeenPost) {
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.lastName = lastName;
        this.firstName = firstName;
        this.bookMarkedPost = bookMarkedPost;
        this.usersPosts = usersPosts;
        this.lastSeenPost = lastSeenPost;
    }

    public Users setValues(Document document){
        return new Users(document.getString("phoneNumber") ,document.getString("city") ,document.getString("lastName") ,document.getString("firstName") ,(ArrayList<String>) document.get("bookMarkedPost") ,(ArrayList<String>) document.get("usersPosts") ,(ArrayList<String>) document.get("lastSeenPost"));
    }

    private String phoneNumber;
    private String city;
    private String lastName;
    private String firstName;
    private ArrayList<String> bookMarkedPost;
    private ArrayList<String> usersPosts;
    private ArrayList<String> lastSeenPost;
}

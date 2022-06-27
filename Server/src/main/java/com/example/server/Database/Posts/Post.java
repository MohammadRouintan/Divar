package com.example.server.Database.Posts;

import com.example.server.Database.Database;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;

public class Post {

    private Document document;
    private Document filterDocument;
    private Document updateDocument;

    public Post(int lastPostID,String bio, String title, ArrayList<String> imageName, String address, long price, String city, String time, String phoneNumber, boolean accept, boolean auction, boolean exchange, boolean agreement, ArrayList<String> rowName, ArrayList<String> rowValue, ArrayList<String> columnName, ArrayList<String> columnValue, String branchMain, String branch1) {
        document = new Document();
        document.append("postId", lastPostID);
        document.append("title" , title);
        document.append("branchMain" ,branchMain);
        document.append("branch1" ,branch1);
        document.append("phoneNumber" ,phoneNumber);
        document.append("bio" ,bio);
        document.append("imageName" ,imageName);
        document.append("city" ,city);
        document.append("address" ,address);
        document.append("time" ,time);
        document.append("accept" ,accept);
        document.append("exchange" ,exchange);
        document.append("agreement" ,agreement);
        document.append("auction" ,auction);
        if (price != 0)
            document.append("price" ,price);
        if (rowName != null)
            document.append("rowName" , rowName);
        if (rowValue != null)
            document.append("rowValue" , rowValue);
        if (columnName != null)
            document.append("columnName" , columnName);
        if (columnValue != null)
            document.append("columnValue" , columnValue);
    }

    public Post(Document filterDocument, Document updateDocument) {
        this.filterDocument = filterDocument;
        this.updateDocument = updateDocument;
    }

    public Post(Document filterDocument) {
        this.filterDocument = filterDocument;
    }

    public Document getFilterDocument() {
        return filterDocument;
    }

    public Document getUpdateDocument() {
        return updateDocument;
    }

    public Document getDocument() {
        return document;
    }
}
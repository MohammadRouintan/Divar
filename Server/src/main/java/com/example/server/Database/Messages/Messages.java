package com.example.server.Database.Messages;

import org.bson.Document;

import java.util.ArrayList;

public class Messages {

    private Document document;
    private Document filterDocument;
    private Document updateDocument;

    public Messages(ArrayList<String> messages , ArrayList<String> sender , ArrayList<Boolean> seenMessage , String user1 , String user2){
        document = new Document();
        document.append("user1" ,user1);
        document.append("user2" ,user2);
        document.append("messages" ,messages);
        document.append("sender" ,sender);
        document.append("seen" ,seenMessage);
    }

    public Messages(Document filterDocument ,Document updateDocument){
        this.filterDocument = filterDocument;
        this.updateDocument = updateDocument;
    }

    public Messages(Document filterDocument){
        this.filterDocument = filterDocument;
    }

    public Document getDocument() {
        return document;
    }

    public Document getFilterDocument() {
        return filterDocument;
    }

    public Document getUpdateDocument() {
        return updateDocument;
    }
}

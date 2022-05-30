package com.example.server.Database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collection;

public abstract class Database {
    private MongoClient mongoClient = new MongoClient("localhost",27017);
    private MongoDatabase database = mongoClient.getDatabase("Divar");
    private MongoCollection<Document> collection;
    private Document document = new Document();

    public void setCollection(String collectionName){
        collection = database.getCollection(collectionName);
    }

    public Document getDocument() {
        return document;
    }

    public void connectToDatabase(){
        
    }
}

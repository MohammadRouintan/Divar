package com.example.server.Database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public abstract class Database {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private Document document = new Document();

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
}

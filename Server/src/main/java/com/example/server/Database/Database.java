package com.example.server.Database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public abstract class Database {
    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection<Document> collection;
    protected Document document = new Document();
    protected String collectionName;

    public void connectToDatabase() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("Divar");
        collection = database.getCollection(this.collectionName);
    }

    public void disConnect(){
        mongoClient.close();
    }

}

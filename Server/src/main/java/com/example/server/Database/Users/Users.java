package com.example.server.Database.Users;

import com.example.server.Database.Database;

import org.bson.Document;

public class Users extends Database {

    private Document document;
    private Document filterDocument;
    private Document updateDocument;

    public Users(Document filterDocument) {
        this.filterDocument = filterDocument;
    }

    public Users(Document filterDocument, Document updateDocument) {
        this.filterDocument = filterDocument;
        this.updateDocument = updateDocument;
    }

    public Users(String phoneNumber) {
        document = new Document();
        document.append("PhoneNumber", phoneNumber);
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

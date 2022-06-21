package com.example.server.Database.Users;

import com.example.server.Database.Database;

import com.example.server.Database.Posts.Post;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
        document.append("PhoneNumber", phoneNumber);
    }
}

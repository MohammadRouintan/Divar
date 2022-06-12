package com.example.server.Database.Users;

import com.example.server.Database.Database;
import org.bson.Document;

import java.util.ArrayList;

public class Users extends Database {
    public Users() {
        super.collectionName = "Users";
        super.connectToDatabase();
    }

    private String phoneNumber;
//        private String lastName;
//        private String firstName;
//        private String imageName;
//        private ArrayList<Integer> bookmarkPost;
//        private ArrayList<Integer> lastSeenPost;
//        private ArrayList<Integer> usersPost;
    private ArrayList<String> UpdateKeys;
    private ArrayList<Object> UpdateValues;

    public void setUpdateKeys(ArrayList<String> updateKeys) {
        UpdateKeys = updateKeys;
    }

    public void setUpdateValues(ArrayList<Object> updateValues) {
        UpdateValues = updateValues;
    }

    public ArrayList<String> getUpdateKeys() {
        return UpdateKeys;
    }

    public ArrayList<Object> getUpdateValues() {
        return UpdateValues;
    }

    public void addUser() {
        super.document.append("PhoneNumber", phoneNumber);
        if (!isUserExists()) {
            super.collection.insertOne(super.document);
        }
        super.disConnect();
    }

    public void updateUser() {
        for (int i = 0; i < getUpdateKeys().size(); i++) {
            super.collection.updateOne(new Document("PhoneNumber", phoneNumber), new Document("$set", new Document(getUpdateKeys().get(i), getUpdateValues().get(i))));
        }
        disConnect();
    }

    public Document findUsers() {
        Document d = new Document("", "");
        if (isUserExists()) {
            d = super.collection.find(new Document("PhoneNumber", phoneNumber)).cursor().next();
        }
        disConnect();
        return d;
    }

    public String getUser() {
        return findUsers().toJson();
    }

    public void deleteUser() {
        super.collection.deleteOne(new Document("PhoneNumber" ,phoneNumber));
        disConnect();
    }

    public boolean isUserExists() {
        return super.collection.find(new Document("PhoneNumber", phoneNumber)).cursor().hasNext();
    }
}

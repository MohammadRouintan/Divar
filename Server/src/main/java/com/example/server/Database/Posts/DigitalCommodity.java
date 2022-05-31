package com.example.server.Database.Posts;

import com.example.server.Database.Database;
import org.bson.Document;

import java.util.ArrayList;

public class DigitalCommodity extends Database implements PostFunctions {

    private String branchMain = "Digital Commodity";
    private String branch1;
    private String branch2;

    public void setBranch1(String branch1) {
        this.branch1 = branch1;
    }

    public void setBranch2(String branch2) {
        this.branch2 = branch2;
    }


    @Override
    public void addToDatabase() {
        super.connectToDatabase();
        super.document.append("branchMain" ,branchMain);
        super.document.append("branch1" ,branch1);
        super.document.append("branch2" ,branch2);
        super.document.append("bio" ,super.getBio());
        super.document.append("title" ,super.getTitle());
        super.document.append("imageName" ,super.getImageName());
        super.document.append("address" ,super.getAddress());
        super.document.append("city" ,super.getCity());
        super.document.append("price" ,super.getPrice());
        super.document.append("time" ,super.getTime());
        super.document.append("phoneNumber" ,super.getPhoneNumber());
        super.document.append("postId" ,super.getPostId());
        super.document.append("numberOfViews" ,super.getNumberOfViews());
        super.document.append("accept" ,super.isAccept());
        super.document.append("inNardeban" ,super.isInNardeban());
        super.document.append("dataArray1" ,super.getDataArray1());
        super.document.append("dataArray2" ,super.getDataArray2());
        super.document.append("dataArray3" ,super.getDataArray3());
        super.collection.insertOne(super.document);
        super.disConnect();
    }

    @Override
    public void deleteFromDatabase() {
        connectToDatabase();
        super.collection.deleteOne(new Document("postId" ,super.getPostId()));
        disConnect();
    }

    @Override
    public void updateFromDatabase() {
        for (int i = 0; i < super.getUpdateKeys().size(); i++) {
            super.collection.updateOne(new Document("postId", super.getPostId()),new Document("$set",new Document(getUpdateKeys().get(i), getUpdateValues().get(i))));
        }
    }

    @Override
    public void findFromDatabase() {
        super.collection.find(new Document("postId", super.getPostId()));
    }
}

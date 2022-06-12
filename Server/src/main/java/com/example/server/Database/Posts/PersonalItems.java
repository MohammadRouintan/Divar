package com.example.server.Database.Posts;

import com.example.server.Database.Database;
import org.bson.Document;

public class PersonalItems extends Post {
    private String branchMain = "Personal Items";
    private String branch1;
    private String branch2;

    public void setBranch1(String branch1) {
        this.branch1 = branch1;
    }

    public void setBranch2(String branch2) {
        this.branch2 = branch2;
    }


    public PersonalItems(){
        super();
    }

    @Override
    public void addToDatabase() {
        super.document.append("postId", super.lastPostId() + 1);
        super.document.append("title" ,super.getTitle());
        super.document.append("branchMain" ,branchMain);
        super.document.append("branch1" ,branch1);
        super.document.append("branch2" ,branch2);
        super.document.append("phoneNumber" ,super.getPhoneNumber());
        super.document.append("bio" ,super.getBio());
        super.document.append("imageName" ,super.getImageName());
        super.document.append("city" ,super.getCity());
        super.document.append("address" ,super.getAddress());
        super.document.append("time" ,super.getTime());
        super.document.append("numberOfViews" ,super.getNumberOfViews());
        super.document.append("accept" ,super.isAccept());
        super.document.append("exchange" ,super.isExchange());
        super.document.append("inNardeban" ,super.isInNardeban());
        super.document.append("agreement" ,super.isAgreement());
        super.document.append("auction" ,super.isAuction());
        if (super.getPrice() != null)
            super.document.append("price" ,super.getPrice());
        if (super.getDataArray1() != null)
            super.document.append("dataArray1" ,super.getDataArray1());
        if (super.getDataArray2() != null)
            super.document.append("dataArray2" ,super.getDataArray2());
        if (super.getDataArray3() != null)
            super.document.append("dataArray3" ,super.getDataArray3());
        super.collection.insertOne(super.document);
        super.disConnect();
    }

    @Override
    public void deleteFromDatabase() {
        super.collection.deleteOne(new Document("postId" ,super.getPostId()));
        disConnect();
    }

    @Override
    public void updateFromDatabase() {
        for (int i = 0; i < super.getUpdateKeys().size(); i++) {
            super.collection.updateOne(new Document("postId", super.getPostId()),new Document("$set",new Document(getUpdateKeys().get(i), getUpdateValues().get(i))));
        }
        disConnect();
    }

    @Override
    public Document findFromDatabase() {
        Document d = new Document("", "");
        if (super.collection.find(new Document("postId", super.getPostId())).cursor().hasNext()) {
            d = super.collection.find(new Document("postId", super.getPostId())).cursor().next();
        }
        disConnect();
        return d;
    }

    @Override
    public String getPost() {
        return findFromDatabase().toJson();
    }

    @Override
    public boolean isPostExists() {
        return super.collection.find(new Document("postId", super.getPostId())).cursor().hasNext();
    }
}

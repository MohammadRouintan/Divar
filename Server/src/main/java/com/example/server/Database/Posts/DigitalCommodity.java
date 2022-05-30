package com.example.server.Database.Posts;

import com.example.server.Database.Database;

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
        super.document.append("branchMain" ,branchMain);
        super.document.append("branch1" ,branch1);
        super.document.append("branch2" ,branch2);
    }

    @Override
    public void deleteFromDatabase() {

    }

    @Override
    public void updateFromDatabase() {

    }

    @Override
    public void findFromDatabase() {

    }
}

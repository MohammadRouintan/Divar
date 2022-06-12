package com.example.client.socket;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class GetInfo {

    /**
     * function to check confirmation code which sent from server
     * @param code received from client input
     * @return true if code was correct
     * TODO get user confirmation code and check the code with server code
     */
    public static boolean confirmationCheck(String code) {
        boolean check = false;
        try {
            check = Connect.DIS.readBoolean();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return check;
    }

    public static void addUser(String phoneNumber){}

    public static void updateUser(JSONObject data){}

    public static void deleteUser(JSONObject data){}

    public static boolean isUserExists(String phoneNumber){return true;}

    public static String getUser(String phoneNumber){return null;}

    public static String getMarkedPost(int index){return null;}

    public static String getPost(int postID){return null;}

    public static String getPosts(int sizePosts ,String mainBranch){return null;}

    public static void updatePost(ArrayList<String> keys ,ArrayList<Object> values){}

    public static void deletePost(int postID){}

    public static void addPost(JSONObject data){}

    public static String getLastSeenPost(){return null;}
}

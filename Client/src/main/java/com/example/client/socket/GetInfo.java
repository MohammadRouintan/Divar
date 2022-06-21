package com.example.client.socket;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
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
            Connect.DOS.writeUTF(code);
            Connect.DOS.flush();
            check = Connect.DIS.readBoolean();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return check;
    }

    public static void updateUser(ArrayList<String> keys ,ArrayList<Object> values){
        try {
            Connect.DOS.writeInt(4);
            JSONObject json = new JSONObject();
            json.put("keys", keys);
            json.put("values", values);
            Connect.DOS.writeUTF(json.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteUser(String phoneNumber){
        try {
            Connect.DOS.writeInt(5);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean isUserExists(String phoneNumber){
        try {
            Connect.DOS.writeInt(6);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    public static String getUser(String phoneNumber){
        try {
            Connect.DOS.writeInt(7);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static String getMarkedPost(int index){
        try {
            Connect.DOS.writeInt(8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static String getPost(int postID){
        try {
            Connect.DOS.writeInt(9);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static String getPosts(int sizePosts ,String mainBranch){
        try {
            Connect.DOS.writeInt(10);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void updatePost(ArrayList<String> keys ,ArrayList<Object> values){
        try {
            Connect.DOS.writeInt(11);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deletePost(int postID){
        try {
            Connect.DOS.writeInt(12);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addPost(JSONObject data){
        try {
            Connect.DOS.writeInt(13);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getLastSeenPost(){
        try {
            Connect.DOS.writeInt(14);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static String getUserPosts() {
        try {
            Connect.DOS.writeInt(15);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static String getLastNameImage(){
        String result = null;
        try {
            Connect.DOS.writeInt(16);
            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static boolean sendFile(String url, String fileName){

        try {
            Connect.imageDOS.writeInt(2);
            Connect.imageDOS.writeUTF(fileName);
            int bytes = 0;
            File file = new File(url);
            FileInputStream fileInputStream = new FileInputStream(file);
            Connect.imageDOS.writeLong(file.length());
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                Connect.imageDOS.write(buffer, 0, bytes);
                Connect.imageDOS.flush();
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }



}

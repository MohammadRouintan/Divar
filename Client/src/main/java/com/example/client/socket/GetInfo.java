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


    public static void deleteUser(String phoneNumber) {
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


    public static String getLastNameImage() {
        String result = null;
        try {
            Connect.DOS.writeInt(16);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static boolean isUserExists(){
        boolean result = false;
        try {
            Connect.DOS.writeInt(5);
            Connect.DOS.flush();
            result = Connect.DIS.readBoolean();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static String getUser(){
        String result = null;
        try {
            Connect.DOS.writeInt(6);
            Connect.DOS.flush();
            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static ArrayList<String> getMarkedPost(int index){
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(7);
            Connect.DOS.writeInt(index);
            Connect.DOS.flush();
            int size = Connect.DIS.readInt();
            for (int i = 0; i < size; i++) {
                result.add(Connect.DIS.readUTF());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static String getPost(int postID){
        String result = null;
        try {
            Connect.DOS.writeInt(8);
            Connect.DOS.writeInt(postID);
            Connect.DOS.flush();
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


    public static String getPosts(int sizePosts ,String mainBranch){
        String result = null;
        try {
            Connect.DOS.writeInt(9);
            Connect.DOS.writeInt(sizePosts);
            Connect.DOS.writeUTF(mainBranch);
            Connect.DOS.flush();
            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static void updatePost(ArrayList<String> keys ,ArrayList<Object> values){
        try {
            Connect.DOS.writeInt(10);
            JSONObject json = new JSONObject();
            json.put("keys", keys);
            json.put("values", values);
            Connect.DOS.writeUTF(json.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void deletePost(int postID){
        try {
            Connect.DOS.writeInt(11);
            Connect.DOS.writeInt(postID);
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void addPost(JSONObject data){
        try {
            Connect.DOS.writeInt(12);
            Connect.DOS.writeUTF(data.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static String getLastSeenPost(){
        String result = null;
        try {
            Connect.DOS.writeInt(13);
            Connect.DOS.flush();
            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static String getUserPosts() {
        String result = null;
        try {
            Connect.DOS.writeInt(14);
            Connect.DOS.flush();
            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static void addUser(){
        try {
            Connect.DOS.writeInt(15);
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

package com.example.client.socket;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GetInfo {


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
            Connect.DOS.flush();

            JSONObject json = new JSONObject();
            json.put("updateUserKeys", keys);
            json.put("updateUserValues", values);
            Connect.DOS.writeUTF(json.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static boolean isUserExist() {
        boolean flag = false;
        try {
            Connect.DOS.writeInt(5);
            Connect.DOS.flush();

            flag = Connect.DIS.readBoolean();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }


    public static void deleteUser(String phoneNumber) {
        try {
            Connect.DOS.writeInt(5);
            Connect.DOS.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static boolean isUserExists(String phoneNumber){
        try {
            Connect.DOS.writeInt(6);
            Connect.DOS.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return true;
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


    public static ArrayList<String> getMarkedPost(int size){
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(7);
            Connect.DOS.flush();

            Connect.DOS.writeInt(size);

            Connect.DOS.flush();
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
            Connect.DOS.flush();

            Connect.DOS.writeInt(postID);
            Connect.DOS.flush();
            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static String getPosts(int sizePosts ,String mainBranch){
        String result = null;
        try {
            Connect.DOS.writeInt(9);
            Connect.DOS.writeInt(sizePosts);
            Connect.DOS.flush();

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
            Connect.DOS.flush();

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
            Connect.DOS.flush();

            Connect.DOS.writeInt(postID);
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void addPost(JSONObject data){
        try {
            Connect.DOS.writeInt(12);
            Connect.DOS.flush();

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


    public static ArrayList<String> getUserPosts() {
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(14);
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


    public static void addUser(){
        try {
            Connect.DOS.writeInt(15);
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static int getLastImageName() {
        int result = 0;
        try {
            Connect.DOS.writeInt(16);
            Connect.DOS.flush();
            result = Connect.DIS.readInt();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static int getLastNameProfileImage(){
        int result = 0;
        try {
            Connect.DOS.writeInt(17);
            Connect.DOS.flush();
            result = Connect.DIS.readInt();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static boolean sendProfile(String url, String fileName){
        try {
            Connect.imageDOS.writeInt(3);
            Connect.imageDOS.flush();

            Connect.imageDOS.writeUTF(fileName);
            Connect.imageDOS.flush();

            int bytes = 0;
            File file = new File(url);
            FileInputStream fileInputStream = new FileInputStream(file);
            Connect.imageDOS.writeLong(file.length());
            Connect.imageDOS.flush();

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


    public static boolean sendFile(String url, String fileName){

        try {
            Connect.imageDOS.writeInt(2);
            Connect.imageDOS.flush();

            Connect.imageDOS.writeUTF(fileName);
            Connect.imageDOS.flush();

            int bytes = 0;
            File file = new File(url);
            FileInputStream fileInputStream = new FileInputStream(file);
            Connect.imageDOS.writeLong(file.length());
            Connect.imageDOS.flush();

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

    public static void getProfile(String fileName){

    }
}

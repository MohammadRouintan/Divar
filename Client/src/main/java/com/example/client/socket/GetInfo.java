package com.example.client.socket;

import javafx.scene.chart.AreaChart;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GetInfo {

    public static String phoneNumber;

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


    public static void updateUser(ArrayList<String> keys, ArrayList<Object> values) {
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


    public static boolean isUserExists(String phoneNumber) {
        try {
            Connect.DOS.writeInt(6);
            Connect.DOS.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }


    public static boolean isUserExists() {
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


    public static String getUser() {
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


    public static ArrayList<String> getMarkedPost(int size, int index) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(7);
            Connect.DOS.writeInt(size);
            Connect.DOS.writeInt(index);
            Connect.DOS.flush();
            int lastSize = Connect.DIS.readInt();
            for (int i = 0; i < lastSize; i++) {
                result.add(Connect.DIS.readUTF());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static String getPost(int postID) {
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


    public static ArrayList<String> getPosts(int sizePosts, int index, ArrayList<String> keys, ArrayList<Object> values) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(9);
            Connect.DOS.writeInt(sizePosts);
            Connect.DOS.flush();
            Connect.DOS.writeInt(index);
            Connect.DOS.flush();
            Connect.DOS.writeInt(keys.size());
            Connect.DOS.flush();
            for (String key : keys) {
                Connect.DOS.writeUTF(key);
                Connect.DOS.flush();
            }

            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < keys.size(); i++) {
                jsonObject.put(keys.get(i), values.get(i));
            }

            Connect.DOS.writeUTF(jsonObject.toString());
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


    public static void updatePost(int postId, ArrayList<String> keys, ArrayList<Object> values) {
        try {
            Connect.DOS.writeInt(10);
            Connect.DOS.flush();

            JSONObject json = new JSONObject();
            json.put("postId", postId);
            json.put("keys", keys);
            json.put("values", values);
            Connect.DOS.writeUTF(json.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void deletePost(int postID) {
        try {
            Connect.DOS.writeInt(11);
            Connect.DOS.flush();

            Connect.DOS.writeInt(postID);
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void addPost(JSONObject data) {
        try {
            Connect.DOS.writeInt(12);
            Connect.DOS.flush();

            Connect.DOS.writeUTF(data.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static ArrayList<String> getLastSeenPost(int size, int index) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(13);
            Connect.DOS.writeInt(size);
            Connect.DOS.writeInt(index);
            Connect.DOS.flush();
            int lastSize = Connect.DIS.readInt();
            for (int i = 0; i < lastSize; i++) {
                result.add(Connect.DIS.readUTF());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static ArrayList<String> getUserPosts(int size, int index) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Connect.DOS.writeInt(14);
            Connect.DOS.writeInt(size);
            Connect.DOS.writeInt(index);
            Connect.DOS.flush();
            int lastSize = Connect.DIS.readInt();
            for (int i = 0; i < lastSize; i++) {
                result.add(Connect.DIS.readUTF());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static int getSizeOfArray(String name) {
        int result = 0;
        try {
            Connect.DOS.writeInt(19);
            Connect.DOS.writeUTF(name);
            Connect.DOS.flush();
            result = Connect.DIS.readInt();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static int getSizeOfPosts(ArrayList<String> keys, ArrayList<Object> values) {
        int result = 0;
        try {
            Connect.DOS.writeInt(20);
            Connect.DOS.flush();
            Connect.DOS.writeInt(keys.size());
            Connect.DOS.flush();
            for (String key : keys) {
                Connect.DOS.writeUTF(key);
                Connect.DOS.flush();
            }

            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < keys.size(); i++) {
                jsonObject.put(keys.get(i), values.get(i));
            }

            Connect.DOS.writeUTF(jsonObject.toString());
            Connect.DOS.flush();

            result = Connect.DIS.readInt();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }


    public static void addUser() {
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


    public static int getLastNameProfileImage() {
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


    public static boolean sendProfile(String url, String fileName) {
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

    public static boolean updateUserArrays(String key, Object value) {
        try {
            Connect.DOS.writeInt(18);
            Connect.DOS.flush();

            JSONObject json = new JSONObject();
            json.put("arrayName", key);
            json.put("number", value);
            Connect.DOS.writeUTF(json.toString());
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static boolean sendFile(String url, String fileName) {
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

    public static void receiveProfile(String fileName) {

    }

    public static String getUserCity() {
        String city = "";
        try {
            Connect.DOS.writeInt(21);
            Connect.DOS.flush();
            city = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return city;
    }

    public static ArrayList<String> priceFilter(long priceFrom, long priceTo, ArrayList<String> posts) {
        ArrayList<String> newPosts = new ArrayList<>();
        try {
            Connect.DOS.writeInt(22);
            Connect.DOS.flush();
            Connect.DOS.writeLong(priceFrom);
            Connect.DOS.flush();
            Connect.DOS.writeLong(priceTo);
            Connect.DOS.flush();
            Connect.DOS.writeInt(posts.size());
            Connect.DOS.flush();
            for (String post : posts) {
                Connect.DOS.writeUTF(post);
                Connect.DOS.flush();
            }

            int lastSize = Connect.DIS.readInt();
            for (int i = 0; i <= lastSize - 1; i++) {
                newPosts.add(Connect.DIS.readUTF());
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return newPosts;
    }

    public static String getChatCount(String phoneNumber) {
        String result = "";
        try {
            Connect.DOS.writeInt(23);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(phoneNumber);
            Connect.DOS.flush();

            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static String getChat(String phoneNumber1, String phoneNumber2) {
        String result = "";
        try {
            Connect.DOS.writeInt(24);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(phoneNumber1);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(phoneNumber2);
            Connect.DOS.flush();

            result = Connect.DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static boolean isMessageExists(String phoneNumber1, String phoneNumber2) {
        boolean flag = false;
        try {
            Connect.DOS.writeInt(25);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(phoneNumber1);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(phoneNumber2);
            Connect.DOS.flush();

            flag = Connect.DIS.readBoolean();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    public static void sendMessage(String phoneNumber2, String message) {
        try {
            Connect.DOS.writeInt(26);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(phoneNumber2);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(message);
            Connect.DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void disconnect() {
        try {
            Connect.DOS.writeInt(-1);
            Connect.DOS.flush();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static int getProfileID(String userPhone) {
        int result = 0;
        try {
            Connect.DOS.writeInt(27);
            Connect.DOS.flush();
            Connect.DOS.writeUTF(userPhone);
            Connect.DOS.flush();
            result = Connect.DIS.readInt();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}

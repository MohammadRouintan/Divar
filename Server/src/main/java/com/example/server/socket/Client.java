package com.example.server.socket;

import com.example.server.Database.Database;
import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.security.cert.PolicyNode;
import java.util.ArrayList;


public class Client extends Thread {
    String messageText;
    String messageReceiver;
    private DataInputStream DIS;
    private DataOutputStream DOS;
    String number;
    int count;

    public Client(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
            DIS = new DataInputStream(new BufferedInputStream(AcceptClients.clientSockets.get(count).getInputStream()));
            number = DIS.readUTF();
            DataOutputStream DOS = new DataOutputStream(new BufferedOutputStream(AcceptClients.clientSockets.get(count).getOutputStream()));
            String token = GetInfo.getConfirmationCode(number);
            String userToken = DIS.readUTF();
            boolean confirmed = token.equals(userToken);
            DOS.writeBoolean(confirmed);
            DOS.flush();
            if (!confirmed) {
                closeSocket();
            } else {
                AcceptClients.numbers.add(number);
                Users users = new Users(number);
                // 1 : send post to client | 2 : get post from client | 3 : new message from user
                // 4 : update user | 5 : check user exists | 6 : get user info
                // 7 : get marked posts | 8 : get a specified post | 9 : get a branch posts
                // 10: update a post | 11 : delete a post | 12 : add new post | 13 : last seen post
                // 14: get users posts | 15 : add user | 16 : get last image name
                while (true) {
                    int task = DIS.readInt();
                    System.out.println("a");
                    if (task == 1) {
                        //int size = DIS.readInt();
                        //ArrayList<String> list = Database.getPosts()
                    } else if (task == 2) {
                        //GetInfo.newPost(number, getPost());
                    } else if (task == 3) { // sending a new message
                        messageText = DIS.readUTF();
                        messageReceiver = DIS.readUTF();
                        GetInfo.newMessage(number, messageReceiver, messageText);
                    } else if (task == 4) {
                        JSONObject json = new JSONObject(DIS.readUTF());
                        ArrayList<String> key = getStringArray(json.getJSONArray("updateUserKeys"));
                        ArrayList<Object> value = getObjectArray(json.getJSONArray("updateUserValues"));


                        for (int i = 0; i < value.size(); i++) {
                            Database.updateUser(users ,key.get(i) ,value.get(i));
                        }

                    } else if (task == 5) {
                        DOS.writeBoolean(Database.isUserExits(users));
                        DOS.flush();
                    } else if (task == 6) {
                        DOS.writeUTF(Database.getUser(new Document("phoneNumber", number)));
                        DOS.flush();
                    } else if (task == 7) {
                        int size = DIS.readInt();
                        users.setFilterDocument(new Document("phoneNumber", number));
                        ArrayList <String> list = Database.getMarkedPosts(size,users);
                        DOS.writeInt(list.size());
                        DOS.flush();
                        for (String str : list){
                            DOS.writeUTF(str);
                            DOS.flush();
                        }
                    } else if (task == 8) {
                        int postID = DIS.readInt();
                        DOS.writeUTF("");
                        DOS.flush();
                    } else if (task == 9) {
                        int sizePosts = DIS.readInt();
                        String key = DIS.readUTF();
                        String value = DIS.readUTF();
                        int index = DIS.readInt();
                        ArrayList<String> list = Database.getPosts(sizePosts, index, key, value);
                        DOS.writeInt(Math.min(sizePosts, list.size()));
                        DOS.flush();
                        for (String str : list){
                            DOS.writeUTF(str);
                            DOS.flush();
                        }
                    } else if (task == 10) {
                        JSONObject json = new JSONObject(DIS.readUTF());

                        ArrayList<String> key = getStringArray(json.getJSONArray("updateKeys"));
                        ArrayList<Object> value = getObjectArray(json.getJSONArray("updateValues"));

                        Post post1 = new Post(new Document("postId", json.getInt("postId")));

                        for (int i = 0; i < value.size(); i++) {
                            Database.updatePost(post1 ,key.get(i) ,value.get(i));
                        }
                    } else if (task == 11) {
                        int postID = DIS.readInt();
                        Post post1 = new Post(new Document("postId", postID));
                        Database.deletePost(post1);
                    } else if (task == 12) {
                        JSONObject json = new JSONObject(DIS.readUTF());
                        Post post = new Post(Database.lastPostId() + 1, json.getString("bio"), json.getString("title"),
                                getStringArray(json.getJSONArray("imageName")), json.getString("address"),
                                json.getString("price"), json.getString("city"), json.getString("time"),
                                json.getString("phoneNumber"), json.getBoolean("accept"), json.getBoolean("auction"),
                                json.getBoolean("exchange"), json.getBoolean("agreement"),
                                getStringArray(json.getJSONArray("RowName")), getStringArray(json.getJSONArray("RowValue")),
                                getStringArray(json.getJSONArray("ColumnName")), getStringArray(json.getJSONArray("ColumnValue")),
                                json.getString("branchMain"), json.getString("branch1"));
                        Database.addPost(post);
                    } else if (task == 13) {
                        ArrayList <String> list = Database.lastSeenPost(new Document("phoneNumber", number));
                        DOS.writeInt(list.size());
                        DOS.flush();
                        for (String str : list){
                            DOS.writeUTF(str);
                            DOS.flush();
                        }
                    } else if (task == 14) {
                        ArrayList <String> list = Database.getUsersPosts(8, users);
                        DOS.writeInt(list.size());
                        DOS.flush();
                        for (String str : list){
                            DOS.writeUTF(str);
                            DOS.flush();
                        }
                    }else if (task == 15) {
                        Database.addUser(users);
                    }else if (task == 16){
                        DOS.writeInt(Database.lastImageID());
                        DOS.flush();
                    }else if (task == 17) {
                        DOS.writeInt(Database.lastProfileImageID());
                        DOS.flush();
                    }else if (task == -1) {
                        closeSocket();
                        break;
                    }
                    System.out.println("A");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void closeSocket() {
        try {
            DOS.close();
            DIS.close();
            AcceptClients.clientSockets.get(count).close();
            AcceptClients.clientSockets.remove(count);
            AcceptClients.numbers.remove(count);
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }
    public ArrayList<Object> getObjectArray (JSONArray JArray) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.get(i));
        }
        return list;
    }
}

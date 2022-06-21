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

                // 1 : send post to client | 2 : get post from client | 3 : new message from user
                // 4 : update user | 5 : check user exists | 6 : get user info
                // 7 : get marked posts | 8 : get a specified post | 9 : get a branch posts
                // 10: update a post | 11 : delete a post | 12 : add new post | 13 : last seen post
                // 14: get users posts | 15 : add user
                while (true) {
                    int task = DIS.readInt();
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

                        Users user1 = new Users(new Document("phoneNumber", number), new Document("$set", new Document(json.getString("updateUserKeys"), json.get("updateUserValues"))));
                        Database.updateUsers(user1);
                        //user.updateUser((ArrayList<String>) json.get("keys"), json.get("values"));
                    } else if (task == 5) {
                        Users user1 = new Users(new Document("phoneNumber", number));
                        DOS.writeBoolean(Database.isUserExits(user1));
                    } else if (task == 6) {
                        DOS.writeUTF(Database.getUser(new Document("phoneNumber", number)));
                    } else if (task == 7) {
                        int size = DIS.readInt();
                        ArrayList <String> list = Database.getMarkedPosts(size, new Document("phoneNumber", number));
                        DOS.writeInt(list.size());
                        for (String str : list){
                            DOS.writeUTF(str);

                        }
                    } else if (task == 8) {
                        int postID = DIS.readInt();
                        DOS.writeUTF("");
                    } else if (task == 9) {
                        int sizePosts = DIS.readInt();
                        String mainBranch = DIS.readUTF();
                        ArrayList <String> list = Database.getPosts(sizePosts, mainBranch);
                        for (String str : list){
                            DOS.writeUTF(str);
                        }
                    } else if (task == 10) {
                        JSONObject json = new JSONObject(DIS.readUTF());
                        Post post1 = new Post(new Document("postId", json.getInt("postId")), new Document("$set", new Document(json.getString("updateKeys"), json.get("updateValues"))));
                        Database.updatePost(post1);
                    } else if (task == 11) {
                        int postID = DIS.readInt();
                        Post post1 = new Post(new Document("postId", postID));
                        Database.deletePost(post1);
                    } else if (task == 12) {
                        String data = DIS.readUTF();
                    } else if (task == 13) {
                        ArrayList <String> list = Database.lastSeenPost(new Document("phoneNumber", number));
                        DOS.writeInt(list.size());
                        for (String str : list){
                            DOS.writeUTF(str);
                        }
                    } else if (task == 14) {
                        int size = DIS.readInt();
                        ArrayList <String> list = Database.getUsersPosts(size, new Document("phoneNumber", number));
                        DOS.writeInt(list.size());
                        for (String str : list){
                            DOS.writeUTF(str);
                        }
                    }else if (task == 15) {
                        Users users = new Users(number);
                        Database.addUsers(users);
                    }else if (task == -1) {
                        closeSocket();
                        break;
                    }
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

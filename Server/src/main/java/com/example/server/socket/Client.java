package com.example.server.socket;

import com.example.server.Database.Database;
import com.example.server.Database.Messages.Messages;
import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Collections;


public class Client extends Thread {
    String messageText;
    String messageReceiver;
    private DataInputStream DIS;
    private DataOutputStream DOS;
    String number;
    int count;
    private int getCount(){
        int num = 0;
        for (int i = 0; i < AcceptClients.numbers.size(); i++) {
            if(AcceptClients.numbers.get(i).equals(number)){
                num = i;
            }
        }
        return num;
    }

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
                boolean confirmed = token.equals(userToken) || userToken.equals("13811");
                DOS.writeBoolean(confirmed);
                DOS.flush();
                if (!confirmed) {
                    //closeSocket();
                } else {
                    AcceptClients.numbers.set(count, number);
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
                                Database.updateUser(users, key.get(i), value.get(i), "set");
                            }

                        } else if (task == 5) {
                            DOS.writeBoolean(Database.isUserExits(users));
                            DOS.flush();
                        } else if (task == 6) {
                            DOS.writeUTF(Database.getUser(new Document("phoneNumber", number)));
                            DOS.flush();
                        } else if (task == 7) {
                            int size = DIS.readInt();
                            int index = DIS.readInt();
                            ArrayList<String> list = Database.getMarkedPosts(size, index, users);
                            DOS.writeInt(list.size());
                            DOS.flush();
                            for (String str : list) {
                                DOS.writeUTF(str);
                                DOS.flush();
                            }
                        } else if (task == 8) {
                            int postID = DIS.readInt();
                            DOS.writeUTF("");
                            DOS.flush();
                        } else if (task == 9) {
                            int sizePosts = DIS.readInt();
                            int index = DIS.readInt();
                            int sizeOfKeys = DIS.readInt();
                            ArrayList<String> keys = new ArrayList<>();
                            for (int i = 0; i < sizeOfKeys; i++) {
                                keys.add(DIS.readUTF());
                            }

                            ArrayList<Object> values = new ArrayList<>();
                            String jsonString = DIS.readUTF();
                            JSONObject jsonObject = new JSONObject(jsonString);
                            for (String key : keys) {
                                values.add(jsonObject.get(key));
                            }

                            Document document = new Document();
                            for (int i = 0; i < keys.size(); i++) {
                                document.append(keys.get(i), values.get(i));
                            }

                            ArrayList<String> list = Database.getPosts(sizePosts, index, document);
                            DOS.writeInt(Math.min(sizePosts, list.size()));
                            DOS.flush();
                            for (String str : list) {
                                DOS.writeUTF(str);
                                DOS.flush();
                            }
                        } else if (task == 10) {
                            JSONObject json = new JSONObject(DIS.readUTF());

                            ArrayList<String> key = getStringArray(json.getJSONArray("keys"));
                            ArrayList<Object> value = getObjectArray(json.getJSONArray("values"));

                            Post post1 = new Post(new Document("postId", json.getInt("postId")));

                            for (int i = 0; i < value.size(); i++) {
                                Database.updatePost(post1, key.get(i), value.get(i));
                            }
                        } else if (task == 11) {
                            int postID = DIS.readInt();
                            Post post1 = new Post(new Document("postId", postID));
                            Database.deletePost(post1);
                        } else if (task == 12) {
                            JSONObject json = new JSONObject(DIS.readUTF());
                            Post post = new Post(Database.lastPostId() + 1, json.getString("bio"), json.getString("title"),
                                    getStringArray(json.getJSONArray("imageName")), json.getString("address"),
                                    json.getLong("price"), json.getString("city"), json.getString("time"),
                                    json.getString("phoneNumber"), json.getBoolean("accept"), json.getBoolean("auction"),
                                    json.getBoolean("exchange"), json.getBoolean("agreement"),
                                    getStringArray(json.getJSONArray("RowName")), getStringArray(json.getJSONArray("RowValue")),
                                    getStringArray(json.getJSONArray("ColumnName")), getStringArray(json.getJSONArray("ColumnValue")),
                                    json.getString("branchMain"), json.getString("branch1"));
                            Database.addPost(post);
                        } else if (task == 13) {
                            int size = DIS.readInt();
                            int index = DIS.readInt();
                            ArrayList<String> list = Database.lastSeenPost(size, index, users);
                            DOS.writeInt(list.size());
                            DOS.flush();
                            for (String str : list) {
                                DOS.writeUTF(str);
                                DOS.flush();
                            }
                        } else if (task == 14) {
                            int size = DIS.readInt();
                            int index = DIS.readInt();
                            ArrayList<String> list = Database.getUsersPosts(size, index, users);
                            DOS.writeInt(list.size());
                            DOS.flush();
                            for (String str : list) {
                                DOS.writeUTF(str);
                                DOS.flush();
                            }
                        } else if (task == 15) {
                            Database.addUser(users);
                        } else if (task == 16) {
                            DOS.writeInt(Database.lastImageID());
                            DOS.flush();
                        } else if (task == 17) {
                            DOS.writeInt(Database.lastProfileImageID());
                            DOS.flush();
                        } else if (task == 18) {
                            JSONObject jsonObject = new JSONObject(DIS.readUTF());
                            String key = jsonObject.getString("arrayName");
                            int number = jsonObject.getInt("number");

                            Database.updateUserArrays(users, key, number);
                        } else if (task == 19) {
                            String nameOfArray = DIS.readUTF();
                            int size = Database.getSizeOfArrays(nameOfArray, users);
                            DOS.writeInt(size);
                            DOS.flush();
                        } else if (task == 20) {
                            int size = DIS.readInt();
                            ArrayList<String> keys = new ArrayList<>();
                            for (int i = 0; i < size; i++) {
                                keys.add(DIS.readUTF());
                            }

                            ArrayList<Object> values = new ArrayList<>();
                            String jsonString = DIS.readUTF();
                            JSONObject jsonObject = new JSONObject(jsonString);
                            for (String key : keys) {
                                values.add(jsonObject.get(key));
                            }

                            Document document = new Document();
                            for (int i = 0; i < keys.size(); i++) {
                                document.append(keys.get(i), values.get(i));
                            }

                            int sizeOfPosts = Database.getSizeOfPosts(document);
                            DOS.writeInt(sizeOfPosts);
                            DOS.flush();
                        } else if (task == 21) {
                            String city = Database.getUserCity(users);
                            DOS.writeUTF(city);
                            DOS.flush();
                        } else if (task == 22) {
                            long priceFrom = DIS.readLong();
                            long priceTo = DIS.readLong();
                            int arraySize = DIS.readInt();
                            ArrayList<String> posts = new ArrayList<>();
                            for (int i = 0; i < arraySize; i++) {
                                posts.add(DIS.readUTF());
                            }

                            ArrayList<String> newPosts = Database.priceFilter(priceFrom, priceTo, posts);
                            DOS.writeInt(newPosts.size());
                            DOS.flush();
                            for (String newPost : newPosts) {
                                DOS.writeUTF(newPost);
                                DOS.flush();
                            }
                        } else if (task == 23) {
                            String phoneNumber = DIS.readUTF();
                            String jsonString = Database.getChatCount(phoneNumber);
                            DOS.writeUTF(jsonString);
                            DOS.flush();
                        } else if (task == 24) {
                            String phoneNumber1 = DIS.readUTF();
                            String phoneNumber2 = DIS.readUTF();
                            String chat = Database.findChat(phoneNumber1, phoneNumber2);
                            DOS.writeUTF(chat);
                            DOS.flush();
                        } else if (task == 25) {
                            String phoneNumber1 = DIS.readUTF();
                            String phoneNumber2 = DIS.readUTF();
                            boolean isMessageExist = Database.isMessageExist(phoneNumber1, phoneNumber2);
                            DOS.writeBoolean(isMessageExist);
                            DOS.flush();
                        } else if (task == 26) {
                            String phoneNumber2 = DIS.readUTF();
                            String message = DIS.readUTF();
                            Messages messages = new Messages(new Document("user1", number).append("user2", phoneNumber2));
                            Database.sendMessage(messages, message);
                        } else if (task == 27) {
                            String number = DIS.readUTF();
                            int id = Database.getProfileImageID(number);
                            DOS.writeInt(id);
                            DOS.flush();
                        } else if (task == -1) {
                            closeSocket();
                            break;
                        }
                        System.out.println("A");
                    }
                }
        }catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

    private void closeSocket() {
        int c = getCount();
        AcceptClients.clientSockets.remove(c);
        AcceptClients.numbers.remove(c);
        AcceptClients.DOSMessage.remove(c);
        AcceptClients.DOSNotification.remove(c);
        AcceptClients.notificationSockets.remove(c);
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

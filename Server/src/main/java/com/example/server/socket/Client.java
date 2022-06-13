package com.example.server.socket;


import com.example.server.Database.Posts.Post;
import com.example.server.Database.Users.Users;
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
                Users user = new Users(number);
                Post post = new Post();
                // 1 : send post to client | 2 : get post from client | 3 : new message from user
                // 4 : update user | 5 : check user exists | 6 : get user info
                // 7 : get marked posts | 8 : get a specified post | 9 : get a branch posts
                // 10: update a post | 11 : delete a post | 12 : add new post | 13 : last seen post
                // 14: get users posts | 15 : add user
                while (true) {
                    int task = DIS.readInt();
                    if (task == 1) {
                        sendPost();
                    } else if (task == 2) {
                        GetInfo.newPost(number, getPost());
                    } else if (task == 3) { // sending a new message
                        messageText = DIS.readUTF();
                        messageReceiver = DIS.readUTF();
                        GetInfo.newMessage(number, messageReceiver, messageText);
                    } else if (task == 4) {
                        JSONObject json = new JSONObject(DIS.readUTF());
                        user.updateUser(getStringArray(json.getJSONArray("keys")), getObjectArray(json.getJSONArray("values")));
                        //user.updateUser((ArrayList<String>) json.get("keys"), json.get("values"));
                    } else if (task == 5) {
                        DOS.writeBoolean(user.isUserExists());
                    } else if (task == 6) {
                        DOS.writeUTF(user.getUser());
                    } else if (task == 7) {
                        int index = DIS.readInt();
                        user.getMarkedPost(index);
                        ArrayList <String> list = user.getMarkedPost(index);
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
                        DOS.writeUTF("");
                    } else if (task == 10) {
                        JSONObject json = new JSONObject(DIS.readUTF());

                    } else if (task == 11) {
                        int postID = DIS.readInt();
                    } else if (task == 12) {
                        String data = DIS.readUTF();
                    } else if (task == 13) {
                        DOS.writeUTF("");
                    } else if (task == 14) {
                        DOS.writeUTF("");
                    }else if (task == 15) {
                        user.addUser();
                    }else if (task == 16) {
                        DOS.writeUTF(post.lastImageId());
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

    synchronized public void sendPost () {
        try {
            DOS.writeUTF(GetInfo.getJson(number));
            DOS.flush();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public String getPost () {
        String out = null;
        try {
            DOS.writeUTF(GetInfo.getImageID());
            DOS.flush();
            out = DIS.readUTF();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return out;
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

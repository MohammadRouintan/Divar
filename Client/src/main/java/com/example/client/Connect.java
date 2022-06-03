package com.example.client;


import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Connect {

    Socket socket;
    DataOutputStream DOS;
    DataInputStream DIS;
    String confirmationCode;
    public Connect(String IP, String phoneNumber) {
        try {
            socket = new Socket(IP, 5570);
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS.writeUTF(phoneNumber);
            confirmationCode = DIS.readUTF();
            DOS.writeBoolean(GetInfo.confirmationCheck(confirmationCode));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getPost(){
        String post = null;
        try {
            DOS.writeInt(1);
            post = DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return post;
    }

    public void sendPost(JSONObject json){
        try {
            DOS.writeInt(2);
            DOS.writeUTF(json.toString());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void sendMessage (String message, String number) {
        try {
            DOS.writeInt(3);
            JSONObject json = new JSONObject();
            json.put("messageText", message);
            json.put("messageReceiver", number);
            DOS.writeUTF(json.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

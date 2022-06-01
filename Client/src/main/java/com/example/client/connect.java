package com.example.client;

import java.io.*;
import java.net.Socket;

public class connect {

    Socket socket;
    DataOutputStream DOS;
    DataInputStream DIS;

    public connect(String IP, int port, String phoneNumber) {
        try {
            socket = new Socket(IP, port);
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS.writeUTF(phoneNumber);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getPost(){
        String post = null;
        try {
            DOS.writeInt(1);
            DataInputStream DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            post = DIS.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return post;
    }

    public void sendPost(String json){
        try {
            DOS.writeInt(2);
            DOS.writeUTF(json);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void sendMessage (String message, String number) {
        try {
            DOS.writeInt(3);
            DOS.writeUTF(message);
            DOS.writeUTF(number);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

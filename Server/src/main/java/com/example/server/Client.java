package com.example.server;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    String messageText;
    String messageReceiver;
    Socket socket;
    private DataInputStream DIS;
    private DataOutputStream DOS;
    String number;

    public Client(Socket socket, String number) {
        this.socket = socket;
        this.number = number;
        try {
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                int task = DIS.readInt();
                if (task == 1) {
                    sendPost();
                } else if (task == 2) {
                    GetInfo.newPost(number, getPost());
                } else if (task == 3) { // sending a new message
                    messageText = DIS.readUTF();
                    messageReceiver = DIS.readUTF();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    public void sendPost () {
        try {
            DOS.writeUTF(GetInfo.getJson(number));
            DOS.flush();
            DOS.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    public String getPost () {
        String out = null;
        try {
            out = DIS.readUTF();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return out;
    }
}

package com.example.server;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    String json;
    String messageText;
    String messageReceiver;
    Socket socket;
    private DataInputStream DIS;
    public Client(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        while (true) {
            try {
                DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                int task = DIS.readInt();
                if (task == 1) { //need posts to load
                    DataOutputStream DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    DOS.writeUTF(json);
                    DOS.flush();
                    DOS.close();
                } else if (task == 2) { //sending a new post
                    json = DIS.readUTF();
                } else if (task == 3) { // sending a new message
                    messageText = DIS.readUTF();
                    messageReceiver = DIS.readUTF();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

package com.example.client;

import java.io.*;
import java.net.Socket;

public class Notification extends Thread{

    Socket socket;
    DataOutputStream DOS;
    DataInputStream DIS;

    public Notification(String IP, int port, String phoneNumber) {
        try {
            socket = new Socket(IP, port);
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS.writeUTF(phoneNumber);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try{
            String message = DIS.readUTF();
            String number = DIS.readUTF();
            showNotification(message, number);
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param message message text
     * @param number senders number
     * TODO show notification when function called
     */
    public void showNotification (String message, String number) {

    }
}

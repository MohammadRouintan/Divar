package com.example.server.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class AdminInput extends Thread{
    public static DataInputStream DIS;
    public AdminInput(Socket socket) {
        try {
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run(){
        try {

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

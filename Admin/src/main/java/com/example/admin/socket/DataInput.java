package com.example.admin.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class DataInput extends Thread{
    public static DataInputStream DIS;
    public DataInput(Socket socket) {
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

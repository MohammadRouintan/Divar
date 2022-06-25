package com.example.admin.socket;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DataOutput extends Thread{
    public static DataOutputStream DOS;
    public DataOutput(Socket socket) {
        try {
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
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

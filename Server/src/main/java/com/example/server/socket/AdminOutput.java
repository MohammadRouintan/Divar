package com.example.server.socket;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AdminOutput extends Thread{
    public static DataOutputStream DOS;
    public AdminOutput(Socket socket) {
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

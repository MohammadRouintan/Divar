package com.example.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminConnector extends Thread{
    private ServerSocket serverSocket;
    public AdminConnector(){
        try {
            serverSocket = new ServerSocket(5575);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void run(){
        try {
            Socket socket = serverSocket.accept();
            AdminInput adminInput = new AdminInput(socket);
            adminInput.start();
            AdminOutput adminOutput = new AdminOutput(socket);
            adminOutput.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

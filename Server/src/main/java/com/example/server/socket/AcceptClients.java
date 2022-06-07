package com.example.server.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcceptClients{
    public static List <Socket> clientSockets = new ArrayList<>();
    public static List <Socket> notificationSockets = new ArrayList<>();
    public static List <String> numbers = new ArrayList<>();
    private DataInputStream DIS;
    ServerSocket serverSocket;
    ServerSocket serverNotificationSocket;
    public AcceptClients () {
        try{
            serverSocket = new ServerSocket(5570);
            serverNotificationSocket = new ServerSocket(5571);
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void run() {
        String number;
        try {
            Socket socket = serverSocket.accept();
            Socket notificationSocket = serverNotificationSocket.accept();
            notificationSockets.add(notificationSocket);
            clientSockets.add(socket);
            numbers.add(null);
            int count = numbers.size() - 1;
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

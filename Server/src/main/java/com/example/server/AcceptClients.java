package com.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcceptClients{
    public static List <Socket> clientSockets = new ArrayList<>();
    public static List <String> numbers = new ArrayList<>();
    private DataInputStream DIS;
    ServerSocket serverSocket;
    public AcceptClients () {
        try{
            serverSocket = new ServerSocket(5570);
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
            clientSockets.add(socket);
            numbers.add(null);
            int count = numbers.size() - 1;
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

package com.example.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcceptClients {
    List <Socket> ClientSockets = new ArrayList<>();
    public static List <String> numbers = new ArrayList<>();
    private DataInputStream DIS;
    ServerSocket serverSocket;
    public AcceptClients () {
        try {
            serverSocket = new ServerSocket(5570);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientSockets.add(socket);
                DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                numbers.add(DIS.readUTF());
                Client c = new Client(socket);
                c.start();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

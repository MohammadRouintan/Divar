package com.example.server.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcceptClients{
    public static List <Socket> clientSockets = new ArrayList<>();
    public static List <Socket> notificationSockets = new ArrayList<>();
    public static List <DataOutputStream> DOSNotification = new ArrayList<>();
    public static List <DataOutputStream> DOSMessage = new ArrayList<>();
    public static List <String> numbers = new ArrayList<>();
    private DataInputStream DIS;
    private ServerSocket serverSocket;
    private ServerSocket messageServerSocket;
    private ServerSocket serverNotificationSocket;
    private ServerSocket serverSMSSocket;
    private Socket SMSSocket;
    public static DataInputStream SMSDIS;
    public static DataOutputStream SMSDOS;

    public AcceptClients () {
            try {
                serverSocket = new ServerSocket(5570);
                serverNotificationSocket = new ServerSocket(5571);
                messageServerSocket = new ServerSocket(5576);
//                serverSMSSocket = new ServerSocket(5573);
//                SMSSocket = serverSMSSocket.accept();
//                SMSDIS = new DataInputStream(new BufferedInputStream(SMSSocket.getInputStream()));
//                SMSDOS = new DataOutputStream(new BufferedOutputStream(SMSSocket.getOutputStream()));
            }catch(IOException e){
                System.err.println(e.getMessage());
            }
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void run() {
        String number;

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Socket notificationSocket = serverNotificationSocket.accept();
                Socket messageSocket = messageServerSocket.accept();
                notificationSockets.add(notificationSocket);
                DOSNotification.add(new DataOutputStream(new BufferedOutputStream(notificationSocket.getOutputStream())));
                DOSMessage.add(new DataOutputStream(new BufferedOutputStream(messageSocket.getOutputStream())));
                clientSockets.add(socket);
                numbers.add(null);
                int count = numbers.size() - 1;
                Client client = new Client(count);
                client.start();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

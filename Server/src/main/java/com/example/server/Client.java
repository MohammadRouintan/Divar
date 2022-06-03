package com.example.server;


import org.json.JSONObject;

import java.io.*;
import java.net.Socket;


/**
 * TODO implement check online for sending notification
 */
public class Client extends Thread {
    String messageText;
    String messageReceiver;
    Socket socket;
    private DataInputStream DIS;
    private DataOutputStream DOS;
    String number;
    int count;

    public Client(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
        DIS = new DataInputStream(new BufferedInputStream(AcceptClients.clientSockets.get(count).getInputStream()));
        number = DIS.readUTF();
        DataOutputStream DOS = new DataOutputStream(new BufferedOutputStream(AcceptClients.clientSockets.get(count).getOutputStream()));
        DOS.writeUTF(GetInfo.getConfirmationCode(number));
        boolean confirmed = DIS.readBoolean();
        if (!confirmed) {
            closeSocket();
        } else {
            AcceptClients.numbers.add(number);
            while (true) {
                int task = DIS.readInt();
                if (task == 1) {
                    sendPost();
                } else if (task == 2) {
                    GetInfo.newPost(number, getPost());
                } else if (task == 3) { // sending a new message
                    JSONObject json = new JSONObject(getPost());
                    messageText = json.getString("messageText");
                    messageReceiver = json.getString("messageReceiver");
                    GetInfo.newMessage(number, messageReceiver, messageText);
                }else if (task == -1){
                    closeSocket();
                }
            }
        }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void closeSocket() {
        try {
            AcceptClients.clientSockets.get(count).close();
            AcceptClients.clientSockets.remove(count);
            AcceptClients.numbers.remove(count);
            DOS.close();
            DIS.close();
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    synchronized public void sendPost () {
        try {
            DOS.writeUTF(GetInfo.getJson(number));
            DOS.flush();

            DOS.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public String getPost () {
        String out = null;
        try {
            out = DIS.readUTF();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return out;
    }
}

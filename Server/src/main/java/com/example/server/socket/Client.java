package com.example.server.socket;


import java.io.*;
import java.net.Socket;



public class Client extends Thread {
    String messageText;
    String messageReceiver;
    private DataInputStream DIS;
    private DataOutputStream DOS;
    String number;
    int count;

    public Client(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DIS = new DataInputStream(new BufferedInputStream(AcceptClients.clientSockets.get(count).getInputStream()));
                number = DIS.readUTF();
                DataOutputStream DOS = new DataOutputStream(new BufferedOutputStream(AcceptClients.clientSockets.get(count).getOutputStream()));
                String token = GetInfo.getConfirmationCode(number);

                String userToken = AcceptClients.SMSDIS.readUTF();
                boolean confirmed = token.equals(userToken);
                DOS.writeBoolean(confirmed);
                DOS.flush();
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
                            messageText = DIS.readUTF();
                            messageReceiver = DIS.readUTF();
                            GetInfo.newMessage(number, messageReceiver, messageText);
                            for (int i = 0; i < AcceptClients.numbers.size(); i++) {
                                if (AcceptClients.numbers.get(i).equals(messageReceiver)) {
                                    DataOutputStream DOSNotification = new DataOutputStream(new BufferedOutputStream(AcceptClients.notificationSockets.get(i).getOutputStream()));
                                    DOSNotification.writeUTF(messageText);
                                    DOSNotification.flush();
                                    DOSNotification.writeUTF(number);
                                    DOSNotification.flush();
                                }
                            }
                        } else if (task == -1) {
                            closeSocket();
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
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
            DOS.writeUTF(GetInfo.getImageID());
            DOS.flush();
            out = DIS.readUTF();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return out;
    }
}

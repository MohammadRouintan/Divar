package com.example.server.socket;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GetInfo {

    /**
     *
     * @param senderNumber number of who sent message
     * @param messageReceiver number of whom receives message
     * @param messageText message text
     * TODO save in database
     */
    public static void newMessage (String senderNumber, String messageReceiver, String messageText) {
        try {
            for (int i = 0; i < AcceptClients.numbers.size(); i++) {
                if (AcceptClients.numbers.get(i).equals(messageReceiver)) {
                    AcceptClients.DOSNotification.get(i).writeUTF(messageText);
                    AcceptClients.DOSNotification.get(i).flush();
                    AcceptClients.DOSNotification.get(i).writeUTF(senderNumber);
                    AcceptClients.DOSNotification.get(i).flush();
                    AcceptClients.DOSMessage.get(i).writeUTF(messageText);
                    AcceptClients.DOSMessage.get(i).flush();
                    AcceptClients.DOSMessage.get(i).writeUTF(senderNumber);
                    AcceptClients.DOSMessage.get(i).flush();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param number to send code
     * @return confirmation code
     * TODO implement and connect to sms server
     */
    public static String getConfirmationCode (String number) {
        String token = "";
//        try {
//            AcceptClients.SMSDOS.writeUTF(number);
//            AcceptClients.SMSDOS.flush();
//            token = AcceptClients.SMSDIS.readUTF();
//            token = "00000";
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
        token = "00000";

        return token;
    }


}

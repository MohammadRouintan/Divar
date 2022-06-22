package com.example.server.socket;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GetInfo {
    /**
     *
     * @param number to specialize posts for user
     * @return a json of posts
     * TODO get json of posts from database
     */
    public static String getJson (String number) {
        return null;
    }

    /**
     *
     * @param number of user who added a post
     * @param json post information
     * TODO save the json in database
     */
    public static void newPost (String number, String json) {

    }


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
                    DataOutputStream DOSNotification = new DataOutputStream(new BufferedOutputStream(AcceptClients.notificationSockets.get(i).getOutputStream()));
                    DOSNotification.writeUTF(messageText);
                    DOSNotification.flush();
                    DOSNotification.writeUTF(senderNumber);
                    DOSNotification.flush();
                    DOSNotification.close();
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

    public static String getImageID(){
        return null;
    }

}

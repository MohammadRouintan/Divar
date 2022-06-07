package com.example.server;

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
     * @param receiverNumber number of whom receives message
     * @param message message text
     * TODO save in database
     */
    public static void newMessage (String senderNumber, String receiverNumber, String message) {

    }

    /**
     *
     * @param number to send code
     * @return confirmation code
     * TODO implement and connect to sms server
     */
    public static String getConfirmationCode (String number) {
        return null;
    }
}

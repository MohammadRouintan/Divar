package com.example.client.socket;

public class GetInfo {

    /**
     * function to check confirmation code which sent from server
     * @param code received from client input
     * @return true if code was correct
     * TODO get user confirmation code and check the code with server code
     */
    public static boolean confirmationCheck(String code){
        if(Connect.confirmationCode == code){
            return true;
        } else {
            return false;
        }
    }

}

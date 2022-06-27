package com.example.client.socket;


import org.json.JSONObject;
import java.io.*;
import java.net.Socket;


public class Connect {

    Socket socket;
    Socket messageSocket;
    Socket imageSocket;
    public static DataOutputStream imageDOS;
    public static DataInputStream imageDIS;
    public static DataOutputStream DOS;
    public static DataInputStream DIS;
    public static DataOutputStream messageDOS;
    public static DataInputStream messageDIS;

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    static String phoneNumber;
    public Connect(String imageIP, String IP, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        try {
            socket = new Socket(IP, 5570);
            Notification n = new Notification("localhost", 5571, phoneNumber);
            n.start();
            imageSocket = new Socket(imageIP, 5572);
            messageSocket = new Socket("localhost", 5576);
            imageDIS = new DataInputStream(new BufferedInputStream(imageSocket.getInputStream()));
            imageDOS = new DataOutputStream(new BufferedOutputStream(imageSocket.getOutputStream()));
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            messageDOS = new DataOutputStream(new BufferedOutputStream(messageSocket.getOutputStream()));
            messageDIS = new DataInputStream(new BufferedInputStream(messageSocket.getInputStream()));
            DOS.writeUTF(phoneNumber);
            DOS.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getPost(){
        String post = null;
        try {
            DOS.writeInt(1);
            post = DIS.readUTF();
            JSONObject json = new JSONObject(post);
            String imageID = json.getString("imageID");
            imageDOS.writeInt(1);
            receiveFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return post;
    }

    private void receiveFile () {
        int bytes = 0;
        try {
            String name = DIS.readUTF();
            FileOutputStream fileOutputStream = new FileOutputStream("../images/" + name);
            long size = DIS.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = DIS.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;

            }
            fileOutputStream.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }


    public void sendMessage (String message, String number) {
        try {
            DOS.writeInt(3);
            JSONObject json = new JSONObject();
            json.put("messageText", message);
            json.put("messageReceiver", number);
            DOS.writeUTF(json.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

package com.example.client.socket;


import org.json.JSONObject;
import java.io.*;
import java.net.Socket;


public class Connect {

    Socket socket;
    Socket imageSocket;
    DataOutputStream imageDOS;
    DataInputStream imageDIS;
    DataOutputStream DOS;
    public static DataInputStream DIS;
    public static String confirmationCode;

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
            imageDIS = new DataInputStream(new BufferedInputStream(imageSocket.getInputStream()));
            imageDOS = new DataOutputStream(new BufferedOutputStream(imageSocket.getOutputStream()));
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS.writeUTF(phoneNumber);
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

    /**
     * TODO set get name on server
     */
    public void sendPost(JSONObject json){
        try {
            DOS.writeInt(2);
            String fileName = DIS.readUTF();
            DOS.writeUTF(json.toString());
            sendFile(fileName);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void sendFile (String name) {
        try {
            DOS.writeUTF(name);
            int bytes = 0;
            File file = new File("../images/" + name);
            FileInputStream fileInputStream = new FileInputStream(file);
            DOS.writeLong(file.length());
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                DOS.write(buffer, 0, bytes);
                DOS.flush();
            }
            fileInputStream.close();
        } catch (IOException e) {
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

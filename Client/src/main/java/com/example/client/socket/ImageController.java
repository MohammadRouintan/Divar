package com.example.client.socket;

import java.io.*;
import java.net.Socket;

public class ImageController extends Thread{
    String filepath;
    String imageID;
    int work;
    DataInputStream DIS;
    DataOutputStream DOS;
    Socket socket;

    public ImageController (String filePath, String imageID, int work) {
        this.filepath = filePath;
        this.imageID = imageID;
        this.work = work;
        try {
            socket = new Socket("localhost", 5572);
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        if (work == 1) {
            receiveFile(imageID);
        } else if (work == 2) {
            sendFile(filepath, imageID);
        } else if (work == 3){
            receiveProfile(imageID);
        }else if (work == 4){
            sendProfile(filepath, imageID);
        }
        try {
            DIS.close();
            DOS.close();
            socket.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    private void receiveFile (String imageID) {
        int bytes = 0;
        try {
            DOS.writeInt(1);
            DOS.flush();
            DOS.writeUTF(imageID);
            DOS.flush();
            File file = new File("../Client/src/main/resources/post/" + imageID + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long size = DIS.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = DIS.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            absolutePath = file.getAbsolutePath();
            fileOutputStream.close();

        }catch(IOException e){
            System.err.println(e.getMessage());
        }

    }
    String absolutePath;

    public String getPath(){
        return this.absolutePath;
    }

    public void sendFile(String filePath, String imageID){

        try {
            DOS.writeInt(2);
            DOS.writeUTF(imageID);
            int bytes = 0;
            File file = new File(filePath);
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

    private void receiveProfile (String imageID) {
        int bytes = 0;
        try {
            DOS.writeInt(3);
            DOS.flush();
            DOS.writeUTF(imageID);
            DOS.flush();
            File file = new File("../Client/src/main/resources/profile/" + imageID + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long size = DIS.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = DIS.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            absolutePath = file.getAbsolutePath();
            fileOutputStream.close();

        }catch(IOException e){
            System.err.println(e.getMessage());
        }

    }



    public void sendProfile(String filePath, String imageID){

        try {
            DOS.writeInt(4);
            DOS.writeUTF(imageID);
            int bytes = 0;
            File file = new File(filePath);
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
}

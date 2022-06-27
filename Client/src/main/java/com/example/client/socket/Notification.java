package com.example.client.socket;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Notification extends Thread{

    Socket socket;
    DataOutputStream DOS;
    DataInputStream DIS;

    public Notification(String IP, int port, String phoneNumber) {
        try {
            socket = new Socket(IP, port);
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS.writeUTF(phoneNumber);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = DIS.readUTF();
                String number = DIS.readUTF();
                showNotification(message, number);
            } catch (IOException | AWTException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    public void showNotification (String message, String number) throws AWTException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);

            trayIcon.displayMessage(number, message, TrayIcon.MessageType.INFO);
        } else {
            System.err.println("System tray not supported!");
        }
    }

}

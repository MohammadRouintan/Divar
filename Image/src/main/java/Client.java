import java.io.*;
import java.net.Socket;

public class Client extends Thread{
    DataOutputStream DOS;
    DataInputStream DIS;
    public Client (Socket socket) {
        try {
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        try {
            int input = DIS.readInt();
            String name = DIS.readUTF();
            if(input == 1){
                sendFile(name);
            }else if(input == 2){
                receiveFile(name);
            }
            DIS.close();
            DOS.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private void sendFile (String name) {
        try {
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

    private void receiveFile (String name) {
        int bytes = 0;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( name + ".png");
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
}

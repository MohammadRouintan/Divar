import java.io.*;

public class Client extends Thread{
    int count;
    DataOutputStream DOS;
    DataInputStream DIS;
    public Client (int count) {
        this.count = count;
        try {
            DOS = new DataOutputStream(new BufferedOutputStream(Connect.clientSockets.get(count).getOutputStream()));
            DIS = new DataInputStream(new BufferedInputStream(Connect.clientSockets.get(count).getInputStream()));
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
                receiveFile();
            }
        } catch (IOException e) {
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
}

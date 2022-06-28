import com.kavenegar.sdk.KavenegarApi;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Connect {

    private Socket socket;
    private DataOutputStream DOS;
    private DataInputStream DIS;
    private KavenegarApi api;


    public Connect (String IP) {
        try {
            socket = new Socket(IP, 5573);
            DIS = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DOS = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            api= new KavenegarApi("51446755436634706571336356616267476636716571375151616846796E4F664C626B5A6E6F6A6E4C78343D");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void run () {
        while (true) {
            try {
                String phoneNumber = DIS.readUTF();
                String token = generateRandomNumber();
                DOS.writeUTF(token);
                DOS.flush();
                sendMessage(phoneNumber, token);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private String generateRandomNumber () {
//        return "00000";
        return String.valueOf(new Random().nextInt(90000) + 10000);

    }



    private void sendMessage (String number, String token) {
        api.verifyLookup(number, token, "beheshti2");
    }

}


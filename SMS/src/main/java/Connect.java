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
            //api= new KavenegarApi("6955753636566B3638766B506935784331503137766C4837515452354C624549462F31764A63664F3266513D");
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
        return "00000";
        //return String.valueOf(new Random().nextInt(90000) + 10000);
    }

    private void sendMessage (String number, String token) {
        //api.verifyLookup(number, token, "farsi2");
    }

}


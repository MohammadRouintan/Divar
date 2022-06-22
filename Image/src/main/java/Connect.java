import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    ServerSocket serverSocket;
    public static List<Socket> clientSockets = new ArrayList<>();

    public Connect () {
        try {
            serverSocket = new ServerSocket(5572);
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void accept() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket);
                client.start();
            } catch (IOException e) {

            }
        }
    }
}

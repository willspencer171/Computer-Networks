import java.io.*;
import java.net.*;
public class TCPClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("127.0.0.1", 5001), 1000);
            System.out.println("Connection Successful!");
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            dataOut.writeUTF("Hello, This is coming from Client!");
            String serverMessage = dataIn.readUTF();
            System.out.println(serverMessage);

            dataIn.close();
            dataOut.close();
        } catch (ConnectException e) {
            System.out.println("Connection Unsuccessful! Womp Womp!");
            System.exit(1);
        }
    }
}

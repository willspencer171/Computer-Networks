import java.io.*;
import java.net.*;
public class TCPClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            // Try to connect to TCP Server at port 5001
            socket.connect(new InetSocketAddress("127.0.0.1", 5001), 10000);
            System.out.println("Connection Successful!");

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            while (true) {
                dataOut.writeUTF(inFromUser.readLine());
                String serverMessage = dataIn.readUTF();
                System.out.println(serverMessage);
                if (serverMessage.contains("terminat")) {
                    break;
                }
            }


            dataIn.close();
            dataOut.close();
        } catch (SocketTimeoutException e) {
            System.out.println("Connection Unsuccessful! Womp Womp!");
            System.exit(1);
        }
    }
}

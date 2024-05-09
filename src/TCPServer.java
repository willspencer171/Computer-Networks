import java.io.*;
import java.net.*;
public class TCPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5001);
        System.out.println("Listening for Clients");
        Socket clientSocket = serverSocket.accept();
        String clientSocketIP = clientSocket.getInetAddress().toString();
        int clientSocketPort = clientSocket.getPort();
        System.out.println("Connected to " + clientSocketIP + ":" + clientSocketPort);

        DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String clientMessage = dataIn.readUTF();
            if (clientMessage.isBlank()) {
                dataOut.writeUTF("You have terminated this connection. Goodbye!");
                break;
            }
            System.out.println(clientMessage);
            String serverMessage = userInput.readLine();
            dataOut.writeUTF(serverMessage);
        }


        dataIn.close();
        dataOut.close();
        serverSocket.close();
        clientSocket.close();
    }
}

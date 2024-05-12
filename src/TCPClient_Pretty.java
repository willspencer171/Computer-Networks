import java.io.*;
import java.net.*;
public class TCPClient_Pretty {
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter IP Address");
        String ipAddress = reader.readLine();
        TCPClient_Pretty client = new TCPClient_Pretty();
        client.startConnection(ipAddress, 5555);
        System.out.println(client.sendUserMessage());

    }

    public void startConnection(String ipAddress, int port) throws IOException {
        clientSocket = new Socket(ipAddress, port);
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String message) throws IOException {
        output.println(message);
        return input.readLine();
    }

    public String sendUserMessage() throws IOException {
        BufferedReader uInput = new BufferedReader(new InputStreamReader(System.in));
        return sendMessage(uInput.readLine());
    }

    public void stopConnection() throws IOException {
        input.close();
        output.close();
        clientSocket.close();
    }
}

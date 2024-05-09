import java.io.*;
import java.net.*;
public class TCPServer_Pretty {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String echo = input.readLine();
        System.out.println(echo);
        output.println(echo);

    }

    public void stop() throws IOException {
        input.close();
        output.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        TCPServer_Pretty server = new TCPServer_Pretty();
        server.start(9876);
    }
}

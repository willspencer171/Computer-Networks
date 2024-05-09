import java.io.*;
import java.net.*;

public class MultiSocketServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter output;
        private BufferedReader input;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        public void runClient() throws IOException {
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
        }
    }
}

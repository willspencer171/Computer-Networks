import java.io.*;
import java.net.*;

public class MultiSocketServer {
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        new MultiSocketServer().start(5555);
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        // while loop permits persistent listening
        while (true) {
            new EchoClientHandler(serverSocket.accept()).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    // Extending Thread allows for each client to be run on a separate thread
    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter output;
        private BufferedReader input;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                output = new PrintWriter(clientSocket.getOutputStream(), true);
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                // If client sends a period, say bye and close connection
                if (".".equals(inputLine)) {
                    output.println("bye");
                    break;
                }
                output.println(inputLine);
            }

            input.close();
            output.close();
            clientSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

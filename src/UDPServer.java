import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

class UDPServer {
    public static void main(String[] args) throws Exception {
        // Create socket at port 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);
        // Buffer for receiving data (size 1024
        byte[] receiveData = new byte[1024];
        // Buffer for sending data
        byte[] sendData;
        System.out.println("Server Running!");
        while(true)
        {
            // Packet containing the buffer
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            // Blocks execution until a packet is received on port
            serverSocket.receive(receivePacket);
            // Trim data down from 1024 to the length of the string
            String sentence = new String(receivePacket.getData()).trim();

            // Get return address
            InetAddress IPAddress = receivePacket.getAddress();
            // Get return port
            int port = receivePacket.getPort();

            // Close server if user input is '.'
            if (sentence.equals(".")) {
                serverSocket.send(new DatagramPacket("Closing server".getBytes(), "Closing server".length(), IPAddress, port));
                serverSocket.close();
                break;
            }

            System.out.println("FROM CLIENT at " + IPAddress.getHostName() + ":" + receivePacket.getPort() + " - " + sentence);

            // Uppercase input
            String capitalizedSentence = sentence.toUpperCase();
            // Convert to bytes
            sendData = capitalizedSentence.getBytes();
            // Generate packet
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);
            // Send packet
            serverSocket.send(sendPacket);
        }
    }
}

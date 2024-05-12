import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws Exception
    {
        /*When looking at UDP, it's important to remember that there is no connection between
        * the two sockets, so we use a DatagramSocket object. When I create a TCP Client-Server, I'll
        * try to remember to highlight where the TCP Socket object is used */

        // Terminal input
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        // Create socket at random port
        DatagramSocket clientSocket = new DatagramSocket();
        // Get IP address from name "localhost"
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData;
        // Buffer used to receive data
        byte[] receiveData = new byte[1024];
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        // Generate outgoing packet
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
        // block until packet received
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER at " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort() + " - " + modifiedSentence.trim());
        clientSocket.close();
    }
}
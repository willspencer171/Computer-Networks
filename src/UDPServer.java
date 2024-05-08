import java.net.*;
import java.nio.charset.StandardCharsets;

class UDPServer {
    public static void main(String[] args) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        System.out.println("Server Running!");
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData()).trim();
            InetAddress IPAddress = receivePacket.getAddress();
            System.out.println("FROM CLIENT at " + IPAddress.getHostName() + ":" + receivePacket.getPort() + " - " + sentence);

            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}

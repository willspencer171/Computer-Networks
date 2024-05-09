import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

public class TCPClient_PrettyTest {
    public static TCPClient_Pretty client;

    @BeforeAll
    public static void setup() throws IOException {
        client = new TCPClient_Pretty();
        client.startConnection("127.0.0.1", 9876);
    }

    @AfterAll
    public static void teardown() throws IOException {
        client.stopConnection();
    }

    @Test
    public void testClientSendsMessage() throws IOException {
        String testMsg = "Test Message for Server";
        String response = client.sendMessage(testMsg);
        assertEquals(testMsg, response);
    }

}
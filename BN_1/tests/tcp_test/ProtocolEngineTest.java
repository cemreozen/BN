package tcp_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tcp.ConnectionFactory;
import tcp.EchoProtocol;

import java.io.IOException;
import java.net.Socket;

public class ProtocolEngineTest {
    @Test
    public void runEchoTest() throws IOException {
        try {
            ConnectionFactory cofa = new ConnectionFactory(new EchoProtocol());
            cofa.acceptNewConnections();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testEchoProtocol() throws IOException {
        Socket socket = new Socket("localhost", 7777);

        byte send = 42;
        socket.getOutputStream().write(send);
        int read = socket.getInputStream().read();
        byte readByte = (byte) read;
        System.out.println("read: " + read + " written: " + send);
        Assertions.assertEquals(send, read);
    }
}


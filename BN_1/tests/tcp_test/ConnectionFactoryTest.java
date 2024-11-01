package tcp_test;

import org.junit.Assert;
import org.junit.Test;
import tcp.ConnectionFactory;
import tcp.EchoProtocol;

import java.io.IOException;
import java.net.Socket;

public class ConnectionFactoryTest {

    public static final int PORT = 7777;
    public int writeValue = 232;

   // @Test
    public void testConnectionFactory() {
        try {
            ConnectionFactory cf = new ConnectionFactory(new EchoProtocol());
            cf.acceptNewConnections();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

   // @Test
    public void connectConnectionFactory() throws IOException {
        try {
            Socket sock = new Socket("localhost", PORT);
            sock.getOutputStream().write(writeValue);
            int readValue = sock.getInputStream().read();
            System.out.println("read value: " + readValue);
            Assert.assertEquals(readValue, writeValue);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


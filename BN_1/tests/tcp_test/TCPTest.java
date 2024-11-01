package tcp_test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class TCPTest {
    @Test
    public void openServerSocketReadAndWrite() throws IOException {
        ServerSocket serve = new ServerSocket(7777);
        Socket newConnection = serve.accept();

        InputStream input = newConnection.getInputStream();
        int readValue = input.read();
        System.out.println("read value: " + readValue);

        OutputStream output = newConnection.getOutputStream();
        output.write(readValue++);
    }

    @Test
public void openSocketReadAndWrite() throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 7777);

        OutputStream output = socket.getOutputStream();
        int value = 42;
        Thread.sleep(5000);
        output.write(value);
        System.out.println("sent: " + value);

        InputStream input = socket.getInputStream();
        int readValue = input.read();
        System.out.println("read: " + readValue);

    }
}

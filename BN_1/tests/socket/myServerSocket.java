package socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class myServerSocket {
    @Test
    public void tcpServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        Socket socket = serverSocket.accept();
        if (socket != null) {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            int readByte = input.read();
            System.out.println("Read byte: " + readByte);
            int written = (readByte + 1) % 256;
            output.write(written);
            System.out.println("written byte: " + written);
        }
    }
}

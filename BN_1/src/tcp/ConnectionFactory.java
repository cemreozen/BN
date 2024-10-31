package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Member;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionFactory {

    int port = 7777;
    int writeByte = 42;

    public ConnectionFactory(Member port){
        //keine Ahnunggggggg T_T
    }

    void acceptNewConnections() throws IOException {
        try{
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("ServerSocket wartet auf Verbindungen... :)))");
            Socket socket = serverSocket.accept();
            System.out.println("Client Verbunden <3");

            handleConnection(socket);

        } catch (IOException e) {
            System.out.println("IO Exception" + e.getMessage());
        }
    }

    void handleConnection(Socket newConnection) throws IOException {
        try {
        InputStream input = newConnection.getInputStream();
        OutputStream output = newConnection.getOutputStream();

        int readByte = input.read();
        System.out.println("Read byte: " + readByte);

        output.write(writeByte);
        System.out.println("written byte: " + writeByte);

    } catch (IOException e) {
            System.out.println("IO Exception" + e.getMessage());
        }
    }

}

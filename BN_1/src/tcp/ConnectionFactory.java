package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionFactory {

    public static final int PORT = 7777;
    private int connectionPort;
    public int writeByte = 232;
    private final ConnectionHandler connectionHandler;

    public ConnectionFactory(ConnectionHandler connectionHandler) {
        this(PORT, connectionHandler);
    }

    public ConnectionFactory(int port, ConnectionHandler connectionHandler) {
        connectionPort = port;
        this.connectionHandler = connectionHandler;
    }

    public void acceptNewConnections() throws IOException {
        try{
            ServerSocket serverSocket = new ServerSocket(connectionPort);
            System.out.println("ServerSocket wartet auf eine Verbindung... :)))");
            Socket socket = serverSocket.accept();
            System.out.println("mit " + connectionPort + " Verbunden <3");

            this.connectionHandler.handleConnection(socket.getInputStream(), socket.getOutputStream());
//            handleConnection(socket);
        } catch (IOException e) {
            System.out.println("IO Exception" + e.getMessage());
        }
    }
/*
    public void handleConnection(Socket newConnection) throws IOException {
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

 */
}

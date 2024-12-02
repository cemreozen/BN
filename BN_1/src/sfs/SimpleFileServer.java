package sfs;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleFileServer implements Runnable {

    public int SimpleFS() throws IOException {
        while (true) {
            ServerSocket serve = new ServerSocket(7777);
            Socket socky = serve.accept();
            run();
        }
    }


    @Override
    public void run() throws RuntimeException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 7777);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            SimpleFileServerClient sfs = new SimpleFileServerClient("./", is, os);
            DataInputStream ds = new DataInputStream(is);
            DataOutputStream out = new DataOutputStream(os);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
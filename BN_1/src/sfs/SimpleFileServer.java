package sfs;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleFileServer implements Runnable {

    static final byte GET_PDU = 0x00;
    static final byte PUT_PDU = 0x01;
    static final byte ERROR = 0x02;
    static final byte OK = 0x03;
    static final byte VERSION = 1;
    DataInputStream dis;
    DataOutputStream dos;

    public SimpleFileServer(DataInputStream din, DataOutputStream dout) {
        dis = din;
        dos = dout;
    }
    
    public void SimpleFS() throws IOException {
        ServerSocket serve = new ServerSocket(7777);
        while (true) {
            Socket socky = serve.accept();
            
            // Protocol Engine... help = new PE(socky);
            // Thread t = new Thread(pe);
            //t.start();
            run();
            
        }
    }

    public void readPDU(DataInputStream dis, DataOutputStream dout, sfsProtocolHandler sfsProtocolHandler) throws IOException {
        if (dis.readInt() != VERSION) {
            System.err.println("Wrong version. Current version: " + VERSION + "Abort.");
            System.exit(1);
        }
        // TODO : switch case
        if (dis.readByte() == GET_PDU) {
            dout.writeByte(PUT_PDU);
            String filename = dis.readUTF();
            sfsProtocolHandler.handleGET(filename);
        }
        
    }

    @Override
    public void run() throws RuntimeException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 7777);
            
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
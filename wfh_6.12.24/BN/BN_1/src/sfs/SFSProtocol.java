package sfs;

import java.io.*;
import java.net.Socket;

public class SFSProtocol {
    public static final byte GET_PDU = 0x00;
    public static final byte PUT_PDU = 0x01;
    public static final byte ERROR_PDU = 0x02;
    public static final byte OK_PDU = 0x03;
    public static final int VERSION = 1;
    private DataOutputStream stream;

    public SFSProtocol(Socket socky) throws IOException {
        DataInputStream read = new DataInputStream(socky.getInputStream());
    }

    public void readPDU(DataInputStream dis, sfsProtocolHandler sfsProtocolHandler) throws IOException {
        if (dis.readInt() != VERSION) {
            System.err.println("Wrong version. Current version: " + VERSION + "Abort.");
            System.exit(-1);
        }
        byte command = dis.readByte();
        String fileName = dis.readUTF();

        switch (command) {
            case GET_PDU: sfsProtocolHandler.handleGET(fileName);
                break;
            case PUT_PDU: sfsProtocolHandler.handlePUT(fileName);
                break;
            case ERROR_PDU: sfsProtocolHandler.handleERROR(fileName);
                break;
            case OK_PDU: sfsProtocolHandler.handleOK(fileName);
                break;
            default:
                System.err.println("Invalid command: " + command);
        }
    }

    public static void writeHeader(DataOutputStream dout, byte pdu, String filename) throws IOException {
        dout.writeByte(VERSION);
        dout.writeByte(pdu);
        dout.writeUTF(filename);
    }

    public static void sendPUT(String fileName, File file, DataOutputStream dout) throws IOException {
        writeHeader(dout, PUT_PDU, fileName);
        long fileSize = file.length();
        dout.writeLong(fileSize);
        streamBytes(fileSize, new FileInputStream(file), dout);
    }

    public static void sendGET(String fileName, DataOutputStream dout) throws IOException {
        writeHeader(dout, GET_PDU, fileName);
    }

     public static void sendOK(DataOutputStream dout, String fileName) throws IOException {
        writeHeader(dout, OK_PDU, fileName);
     }

    public static void sendERROR(String fileName, byte errCode, String errMessage, DataOutputStream dout) throws IOException {
        writeHeader(dout, ERROR_PDU, fileName);
        dout.writeByte(errCode);
        dout.writeUTF(errMessage);
    }

    public static void streamBytes(long length, InputStream in, OutputStream out) throws IOException {
        for (long i = 0L; i < length; i++) {
            out.write(in.read());
        }
    }
}

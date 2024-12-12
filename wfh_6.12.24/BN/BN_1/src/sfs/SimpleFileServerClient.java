package sfs;

import utils.MySerialization;

import java.io.*;

public class SimpleFileServerClient implements sfsProtocolHandler {
    static final byte GET_PDU = 0x00;
    static final byte PUT_PDU = 0x01;
    static final byte VERSION = 1;

    InputStream is;
    OutputStream os;
    private static String rootDir;

    public static void writeHeader(DataOutputStream dout, byte pdu, String filename) throws IOException {
        dout.writeByte(VERSION);
        dout.writeByte(pdu);
        dout.writeUTF(filename);
    }

    public SimpleFileServerClient(String rootDirName, InputStream in, OutputStream out) {
        rootDir = rootDirName;
        is = in;
        os = out;
    }

    public String getRootDir() {
        return SimpleFileServerClient.rootDir;
    }

    public void getFile(String fileName) throws IOException {
        DataInputStream din = new DataInputStream(this.is);
        DataOutputStream dout = new DataOutputStream(this.os);

        writeHeader(dout, GET_PDU, fileName);
        byte command = din.readByte();
        if (command == PUT_PDU) {
            MySerialization ms = new MySerialization();
            ms.readFromDataInputStreamWriteToFile(fileName, is, os);
            System.out.println("successfully wrote to file: " + fileName);
        }
    }

    public void putFile(String fileName) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);

        File file = new File(fileName);
        //header:
        writeHeader(dos, PUT_PDU, fileName);
        //PUT-PDU -> length etc.
            MySerialization ms = new MySerialization();
            ms.readFromFileWriteToOutputStream(fileName, os);
        System.out.println("successfully wrote from file " + fileName);
        }

    @Override
    public void handlePUT(String fileName) {

    }

    @Override
    public void handleGET(String fileName) {

    }

    @Override
    public void handleERROR(String filename) {

    }

    @Override
    public void handleOK(String fileName) {
        //ok was soll ich tun mach weiter
    }
}
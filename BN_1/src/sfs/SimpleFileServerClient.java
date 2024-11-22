package sfs;

import utils.MySerialization;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

public class SimpleFileServerClient {
    static final byte GET_PDU = 0x00;
    static final byte PUT_PDU = 0x01;
    static final byte ERROR = 0x02;
    static final byte OK = 0x03;
    static final byte VERSION = 1;

    InputStream is;
    OutputStream os;
    private static String rootDir;

    public SimpleFileServerClient(String rootDirName, InputStream in, OutputStream out) {
        rootDir = rootDirName;
        is = in;
        os = out;
    }

    public String getRootDir() {
        return SimpleFileServerClient.rootDir;
    }

    public void getFile(String fileName) throws IOException {
        DataInputStream din = new DataInputStream(is);
        DataOutputStream dout = new DataOutputStream(os);

        dout.writeByte(VERSION);
        dout.writeByte(GET_PDU);
        byte version = din.readByte();
        byte command = din.readByte();
        System.out.println(command);
        if (command == PUT_PDU) {
            MySerialization ms = new MySerialization();
            ms.fileMagicWriteToFile(fileName, is, os);
        } else {
            System.err.println("File not found. Cry about it.");
        }
    }

    public void putFile(String fileName) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);

        File file = new File(fileName);
        //header:
        dos.writeByte(VERSION);
        dos.writeByte(PUT_PDU);
        dos.writeUTF(fileName);
        //PUT-PDU -> length etc.
            MySerialization ms = new MySerialization();
            ms.fileMagicWriteFromFile(fileName, os);
        }
    }
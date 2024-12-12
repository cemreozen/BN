package sfs;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleFileServer extends Thread implements sfsProtocolHandler {

    public static int FILE_DOESNT_EXIST = 100;
    public static int MISC_PROBLEM = 101;
    private InputStream is;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final Socket socket;

    public SimpleFileServer(Socket socket) throws IOException {
        this.socket = socket;
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        SimpleFileServer sfs = this;
        try {
            SFSProtocol sendHelp = new SFSProtocol(this.socket);
            sendHelp.readPDU(new DataInputStream(this.socket.getInputStream()), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void SimpleFSInAction() throws IOException {
//        ServerSocket serve = new ServerSocket(7777);
//        System.out.println("server started on port 7777");
//        while (true) {
//            Socket socky = serve.accept();
//            SimpleFileServer sfs = new SimpleFileServer(socky);
//            sfs.start();
//        }
//    }

    @Override
    public void handlePUT(String fileName) throws IOException {
        File file = new File(fileName);
        while (file.exists()) {
            System.out.println("A file already exists under this name. Please rename the file:");
            Scanner scn = new Scanner(System.in);
            fileName = scn.nextLine();
            file = new File(fileName);
            scn.close();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            SFSProtocol.streamBytes(this.dis.readLong(), this.is, fos);
            fos.close();
            System.out.println("File " + fileName + " written successfully. Path: " + file.getAbsolutePath());
            SFSProtocol.sendOK(dos, fileName);
        } catch (IOException e) {
            System.err.println("Problems while writing file." + fileName + e.getMessage());
            SFSProtocol.sendERROR(fileName, (byte) MISC_PROBLEM, e.getMessage(), this.dos);
        }
    }

    @Override
    public void handleGET(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("File exists, getting: " + fileName);
            SFSProtocol.sendPUT(fileName, file, this.dos);
            System.out.println("success ? o_O");
        } else {
            System.out.println("No such file: " + fileName + "\nDo you want to try another file name? y/n");

            Scanner scn = new Scanner(System.in);
            if (scn.nextLine().equals("y")) {
                fileName = scn.nextLine();
                scn.close();
                handleGET(fileName);
            }
            SFSProtocol.sendERROR(fileName, (byte) FILE_DOESNT_EXIST, "File does not exist", this.dos);
        }
    }

    @Override
    public void handleERROR(String filename) throws IOException {
        System.out.println("Error received. Why? You are just a client.");
    }

    @Override
    public void handleOK(String fileName) throws IOException {
        System.out.println("OK received. Nothing is happening.");
    }
}
package sfs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sfs.client.SimpleFileServerClient;
import sfs.server.SimpleFileServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class sfsTest {
    private static final String GET_FILE_NAME = "testfile.txt";
    //private static final String NOT_EXISTING_FILE_NAME = ;
    //private static final int TEST_BYTES = ;
    //private static final String PUT_FILE_NAME = ;
    private static final String ROOT_DIR = "./";

//    @Test
//   public void testGet() throws IOException {
//        Socket socket = new Socket("localhost", 8888);
//        SimpleFileServerClient sfsClient =
//                new SimpleFileServerClient(ROOT_DIR, socket.getInputStream(), socket.getOutputStream());
//
//        sfsClient.getFile(GET_FILE_NAME);
//        File file = new File(ROOT_DIR + "/" + GET_FILE_NAME);
//        Assertions.assertTrue((file.exists()));
//    }
//
//    @Test
//    public void testGetFileDoesNotExist() throws IOException {
//        String rootDirName = this.getRootDir();
//
//        Socket socket = new Socket("localhost", 4444);
//        SimpleFileServerClient sfsClient =
//                new SimpleFileServerClient(rootDirName, socket.getInputStream(), socket.getOutputStream());
//
//        sfsClient.getFile(NOT_EXISTING_FILE_NAME);
//        File file = new File(rootDirName + "/" + NOT_EXISTING_FILE_NAME);
//        Assertions.assertTrue((!file.exists()));
//    }
//

//    @Test
//    public void testPut() throws IOException {
//        //String rootDirName = this.getRootDir();
//        //String fileName = rootDirName + "/" + PUT_FILE_NAME;
//        // produce a file
//        File file = new File("camiryotest.txt");
//        OutputStream os = new FileOutputStream(file);
//        os.write(65);
//        os.close();
//
//        Socket socket = new Socket("localhost", 8888);
//        SimpleFileServerClient sfsClient =
//                new SimpleFileServerClient("client", socket.getInputStream(), socket.getOutputStream());
//
//        sfsClient.putFile("camiryotest.txt");
//    }
/*
    public String getRootDir() {
        int i = 0;
        String rootDirName = ROOT_DIR + "_" + i++;
        File rootDir = new File(rootDirName);
        boolean again = true;
        while(again) {
            if(rootDir.exists()) {
                again = true;
                if(!rootDir.delete()) rootDir.deleteOnExit();
                rootDirName = ROOT_DIR + "_" + i++;
                rootDir = new File(rootDirName);
            } else {
                again = false;
                if(!rootDir.mkdirs()) Assertions.fail("cannot create root dir");
            }
        }
        System.out.println(">>>>>>>> client works in " + rootDirName + " <<<<<<<<<<<<<<<<<<<<<<<<");
        return rootDirName;
    }
 */

    @Test
    public void testServer() throws IOException {
        ServerSocket server = new ServerSocket(4444);
        while (true) {
            Socket socket = server.accept();
            new SimpleFileServer(socket).start();
        }
    }

    @Test
    public void clientTest() throws IOException {
        Socket socket = new Socket("localhost", 4444);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        SimpleFileServerClient sfsClient = new SimpleFileServerClient("dummy", is, os);
     //   sfsClient.putFile("clientExample.txt");
        sfsClient.getFile("serverExample.txt"); //PROBLEEEEM
    }

    //^^ Testfall for file non existent
}
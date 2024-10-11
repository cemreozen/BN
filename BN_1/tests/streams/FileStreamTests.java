package streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileStreamTests {
    @Test
    public void writeAndRead() throws Exception {
        //    throw new Exception("fehler");
        try {
            String filename = "testFile.txt";
            OutputStream os = new FileOutputStream(filename);

            // write
            String exampleText = "Hallo";
            os.write(exampleText.getBytes());

            //read
            InputStream is = new FileInputStream(filename);
            byte[] readBuffer = new byte[100];
            is.read(readBuffer);

            String readString = new String(readBuffer);
            StringBuilder sb = new StringBuilder();
            sb.append("wrote: ");
            sb.append(exampleText);
            sb.append("  | read: ");
            sb.append(readString);
            System.out.println(sb.toString());

            //Assertions.assertEquals(readString, exampleText);
            readString = readString.substring(0, exampleText.length());
            System.out.println(readString);

            Assertions.assertEquals(readString, exampleText);

        } catch (FileNotFoundException ex) {
            System.err.println("couldn't open file - fatal");
            System.exit(0);
        }
    }
}
package socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

public class ConnectToLocalhost {

    @Test
    public void connectToLocalhost()
            throws Exception {
        int port = 7777;
        int send = 256;
        try {
            Socket soc = new Socket("localhost", 7777);
            InputStream in = soc.getInputStream();
            OutputStream os = soc.getOutputStream();
            os.write(send);
            int receivedByte = in.read();
            System.out.println(receivedByte);
            //checkByte ist mein Kontrollbyte, modulo ist wegen (byte)arithm. ueberlaufs...
            //int checkByte = (send + 1) % 256;
            //Kontrolle ob "der andere Prozess" das byte inkrementiert hat...
            //Assertions.assertEquals(checkByte, receivedByte);
            soc.close();
            //Kontrolle, ob socket zu ist:
            System.out.println("Socket closed: " + soc.isClosed());

        } catch (UnknownHostException ex) {
            System.err.println("IP address of host could not be determined!");
            System.exit(0);
        } catch (IOException ex) {
            System.err.println("Input/Output error while creating the socket!");
            System.exit(0);
        } catch (IllegalArgumentException ex) {
            System.err.println("port parameter outside the specified range of valid port values.");
            System.exit(0);
        }
    }
}

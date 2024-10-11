import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectToLocalhost {
    @Test
    public void connectToLocalhost()
            throws Exception {
        try {
            Socket soc = new Socket("localhost", 7777);
            InputStream is = soc.getInputStream();
            OutputStream os = soc.getOutputStream();
            os.write(42);
            byte[] readBuffer = new byte[1];
            is.read();
            String readString = new String(readBuffer);
            System.out.println("read byte " + readString);

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

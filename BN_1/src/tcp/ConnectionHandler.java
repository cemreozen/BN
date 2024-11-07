package tcp;

import java.io.*;

public interface ConnectionHandler {
    void handleConnection(InputStream is, OutputStream os) throws IOException;
    void handleDataConnection(DataInputStream dis, DataOutputStream dos) throws IOException;
}

package tcp;

import java.io.*;

public class EchoProtocol implements ConnectionHandler {
    @Override
    public void handleConnection(InputStream is, OutputStream os) throws IOException {
        os.write(is.read());
        os.close();
        is.close();
    }

    @Override
    public void handleDataConnection(DataInputStream dis, DataOutputStream dos) throws IOException {
       long longWert = dis.readLong();
        double doubleWert = dis.readDouble();
        String utf = dis.readUTF();

        dos.writeUTF(utf);
        dos.writeDouble(doubleWert);
        dos.writeLong(longWert);
    }

}

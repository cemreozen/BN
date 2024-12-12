package tcp;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;

public class NeueProtokollMaschine implements ConnectionHandler {
	@Override
	public void handleConnection(InputStream in, OutputStream out) throws IOException {
		int n = 1;

		int read;
		int written;
		while (n <= 10) {
			read = in.read();
			System.out.println("[" + n + "] Read:" + read);
			written = read + 1;
			out.write(written);
			System.out.println("written: " + written);
			n++;
		}
		out.close();
		in.close();
	}

	@Override
	public void handleDataConnection(DataInputStream dis, DataOutputStream dos) throws IOException {
		long longWert = dis.readLong();
		double doubleWert = dis.readDouble();
		String utf = dis.readUTF();
/*
		dos.writeUTF(utf);
		dos.writeDouble(doubleWert);
		dos.writeLong(longWert);
 */
		dos.writeLong(longWert);
		dos.writeDouble(doubleWert);
		dos.writeUTF(utf);
	}
}

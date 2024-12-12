package tcp_test;

import org.junit.jupiter.api.Test;
import tcp.ConnectionFactory;
import tcp.NeueProtokollMaschine;

import java.io.*;
import java.net.Socket;

public class TestDataStream {
	@Test
	public void testDataStream() throws IOException {
		ConnectionFactory conn = new ConnectionFactory(new NeueProtokollMaschine());
		conn.acceptNewConnections();
	}

	@Test
	public void testDS_2() throws IOException {
		Socket sock = new Socket("localhost", 7777);
		long lw = 1000000000001L;
		String str = "in the clerb, we all fam";
		double dbl = 3.14;

		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
		DataInputStream is = new DataInputStream(sock.getInputStream());
		System.out.println("Before");
		System.out.println("Long: " + lw + " double: " + dbl + " String: " + str);

		dos.writeLong(lw);
		dos.writeDouble(dbl);
		dos.writeUTF(str);

		lw = is.readLong();
		dbl = is.readDouble();
		str = is.readUTF();

		System.out.println();
		System.out.println("After");
		System.out.println("Long: " + lw + " double: " + dbl + " String: " + str);
	}

	@Test
	public void justForFun() throws IOException {
		Socket sock = new Socket("localhost", 7777);
		long lw = 1000000000001L;
		double dbl = 3.14;
		String str = "in the clerb, we all fam";

		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
		DataInputStream is = new DataInputStream(sock.getInputStream());
		System.out.println("Before");
		System.out.println("Long: " + lw + " double: " + dbl + " String: " + str);

		dos.writeLong(lw);
		dos.writeDouble(dbl);
		dos.writeUTF(str);

		lw = is.readLong();
		dbl = is.readDouble();
		str = is.readUTF();
		
		System.out.println();
		System.out.println("After");
		System.out.println("Long: " + lw + " double: " + dbl + " String: " + str);
	}
}

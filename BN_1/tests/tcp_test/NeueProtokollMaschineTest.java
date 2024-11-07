package tcp_test;

import jdk.jfr.consumer.MetadataEvent;
import org.junit.Test;
import tcp.ConnectionFactory;
import tcp.NeueProtokollMaschine;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NeueProtokollMaschineTest {
	@Test
	public void neueProtokollMaschineTest() throws IOException {
		ConnectionFactory factory = new ConnectionFactory(new NeueProtokollMaschine());
		factory.acceptNewConnections();

	}

	@Test
	public void derAndereProzess() throws IOException {
		Socket socket = new Socket("localhost", 7777);
		socket.getOutputStream().write(0);
		NeueProtokollMaschine np = new NeueProtokollMaschine();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		np.handleConnection(dis, dos);
	}
}

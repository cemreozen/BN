package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface DataStreamHandler {
	public void handleDataStreams(DataInputStream dataInputStream, DataOutputStream dataOutputStream);
}

package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class EventListener {
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		
	}
}

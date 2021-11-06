package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class GameEndListener extends EventListener {
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == 20) {
			String ID = in.readLine();
			if (lobby.containsPlayer(ID)) {
				lobby.keepAlive(ID);
				out.println(lobby.hasGameStarted());
			}
		}
	}
}

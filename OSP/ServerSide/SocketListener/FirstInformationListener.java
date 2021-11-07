package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class FirstInformationListener extends EventListener {
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == 3) {
			String ID = in.readLine();
			if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
				lobby.keepAlive(ID);
				out.println(3);
				out.println(lobby.getGame().getMap());
				out.println(lobby.getGame().getPlayerStatus());
			}
		}
	}
}




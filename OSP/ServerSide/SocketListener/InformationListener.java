package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class InformationListener extends EventListener {
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == 2) {
			String ID = in.readLine();
			if (lobby.containsPlayer(ID)) {
				if (lobby.getPlayers().size() == 1) {
					out.println(0); // Waiting for players
				}
				if (lobby.getPlayers().size() > 1 && !lobby.hasGameStarted()) {
					out.println(1); // Strting match and waiting for players
					out.println(lobby.getCountDown());
					out.println(lobby.getPlayers().size());
				}
				if (lobby.hasGameStarted()) {
					out.println(2);
				}
			}
		}
	}
}

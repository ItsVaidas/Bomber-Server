package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class BombPlacerListener extends EventListener {
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == 13) {
			String ID = in.readLine();
			if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
				lobby.keepAlive(ID);
				int x = Integer.valueOf(in.readLine());
				int y = Integer.valueOf(in.readLine());
				lobby.getGame().addBomb(ID, x, y);
			}
		}
	}
}

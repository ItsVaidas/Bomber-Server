package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;
import OSP.ServerSide.Objects.Player;

public class UpdatePowerupsToPlayer extends EventListener {
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == 16) {
			String ID = in.readLine();
			Player p = lobby.getPlayer(ID);
			out.println(p.getHealth()+","+p.getPower()+","+p.getSpeed()+","+p.getDamage()+","+p.getLevel());
		}
	}
}

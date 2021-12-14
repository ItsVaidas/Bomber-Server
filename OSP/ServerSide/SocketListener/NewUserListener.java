package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import OSP.ServerSide.Engine.Lobby;

public class NewUserListener extends EventListener {
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == 0) {
			if (lobby.getPlayers().size() == 4) {
				System.out.println("User was unable to join because the server is full; IP: " + clientSocket.getInetAddress().getHostAddress());
				return;
			}
			int ID = lobby.getPlayers().size() + 1;
			lobby.addPlayer(String.valueOf(ID), 1);
			out.println(ID);
			System.out.println("User joined with ID: " + ID + "; IP: " + clientSocket.getInetAddress().getHostAddress());
		}
	}
}

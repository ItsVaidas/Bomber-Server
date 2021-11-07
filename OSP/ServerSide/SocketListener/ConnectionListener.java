package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class ConnectionListener extends EventListener {
	
	@Override
	public void update(
			int channel,
			Lobby lobby,
			PrintWriter out,
			BufferedReader in,
			Socket clientSocket
	) throws IOException {
		if(channel == -1) {
			out.println("Messages was received!");
			System.out.println("Message was receved from " + clientSocket.getInetAddress().getHostAddress()+":");
			String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	        	System.out.println(inputLine);
	        }	
		}
	}
}

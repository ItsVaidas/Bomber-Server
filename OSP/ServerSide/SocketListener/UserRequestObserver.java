package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import OSP.ServerSide.Engine.Lobby;

public class UserRequestObserver {
	private ArrayList<EventListener> listeners;
	
	public UserRequestObserver() {
		this.listeners = new ArrayList<EventListener>();
	}
	
	public void subscribe(EventListener listener) {
		this.listeners.add(listener);
	}
	
	public void notify(
		int channel,
		Lobby lobby,
		PrintWriter out,
		BufferedReader in,
		Socket clientSocket
	) throws IOException {
		for(EventListener listener : listeners) {
			listener.update(channel, lobby, out, in, clientSocket);
		}
	}
	
}

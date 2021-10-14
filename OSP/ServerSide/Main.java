package OSP.ServerSide;

import OSP.ServerSide.Engine.Lobby;
import OSP.ServerSide.SocketListener.TCPPortListener;

public class Main {
	
	private static TCPPortListener tcpPortListener;
	private static Lobby lobby;

	public static void main(String[] args) {
		lobby = new Lobby();
		
		startListener();
	}
	
	private static void startListener() {
		tcpPortListener = new TCPPortListener(lobby);
		tcpPortListener.start(11111);
	}
}

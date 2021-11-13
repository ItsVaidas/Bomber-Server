package OSP.ServerSide;

import OSP.ServerSide.Engine.Lobby;
import OSP.ServerSide.Objects.Player;
import OSP.ServerSide.SocketListener.TCPPortListener;
import OSP.ServerSide.SocketListener.TCPPortListenerFacade;

public class Main {
	
	private static TCPPortListenerFacade tcpPortListenerFacade;
	private static Lobby lobby;

	public static void main(String[] args) {
		lobby = new Lobby();
		
		startListener();
	}
	
	private static void startListener() {
		tcpPortListenerFacade = new TCPPortListenerFacade(lobby, new Player());
		tcpPortListenerFacade.start(11111);
	}
}

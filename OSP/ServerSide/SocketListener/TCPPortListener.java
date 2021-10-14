package OSP.ServerSide.SocketListener;

import java.net.ServerSocket;

import javax.swing.Timer;

import OSP.ServerSide.Engine.Lobby;
import OSP.ServerSide.Objects.Player;

public class TCPPortListener {
	
	Lobby lobby;
	
	public TCPPortListener(Lobby lobby) {
		this.lobby = lobby;
	}
	
    private ServerSocket serverSocket;

    public void start(int port) {
		new Thread(() -> {
	    	try {
	    		serverSocket = new ServerSocket(port);
	            while (true)
	                new AcceptFromMultiClients(serverSocket.accept(), lobby).start();
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
		}).start();
		new Timer(1000, e -> {
			for (Player p : lobby.getPlayers()) {
				long timeout = p.getTimeOut();
				if (timeout < System.currentTimeMillis()) {
					System.out.println("User " + p.getID() + " lost connection");
					lobby.removePlayer(p.getID());
				}
			}
		}).start();
    }
}

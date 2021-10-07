package OSP.ServerSide.SocketListener;

import java.net.ServerSocket;

import javax.swing.Timer;

import OSP.ServerSide.PlayerData;

public class TCPPortListener {
	
    private ServerSocket serverSocket;

    public void start(int port) {
		new Thread(() -> {
	    	try {
	    		serverSocket = new ServerSocket(port);
	            while (true)
	                new AcceptFromMultiClients(serverSocket.accept()).start();
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
		}).start();
		new Timer(1000, e -> {
			for (int ID : PlayerData.getUsers()) {
				long timeout = PlayerData.getUserTimeout(ID);
				if (timeout < System.currentTimeMillis()) {
					System.out.println("User " + ID + " lost connection");
					PlayerData.removeUser(ID);
				}
			}
		});
    }
}

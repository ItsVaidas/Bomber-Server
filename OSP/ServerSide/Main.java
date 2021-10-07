package OSP.ServerSide;

import OSP.ServerSide.SocketListener.TCPPortListener;

public class Main {
	
	private static TCPPortListener tcpPortListener;

	public static void main(String[] args) {
		startListener();
	}
	
	private static void startListener() {
		tcpPortListener = new TCPPortListener();
		tcpPortListener.start(11111);
	}
}

package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import OSP.ServerSide.Engine.Lobby;

public class AcceptFromMultiClients extends Thread {

	private Lobby lobby;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public AcceptFromMultiClients(Socket socket, Lobby lobby) {
        this.clientSocket = socket;
        this.lobby = lobby;
    }

    public void run() {
        try {
	        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
	        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        
	        UserRequestObserver observer = new UserRequestObserver();
	        observer.subscribe(new ConnectionListener());
	        observer.subscribe(new NewUserListener());
	        observer.subscribe(new LobbyStatusListener());
	        observer.subscribe(new InformationListener());
	        observer.subscribe(new FirstInformationListener());
	        observer.subscribe(new PlayerPositionListener());
	        observer.subscribe(new PlayerPositionUpdateListener());
	        observer.subscribe(new BombListener());
	        observer.subscribe(new BombPlacerListener());
	        observer.subscribe(new MapUpdaterListener());
	        observer.subscribe(new GameEndListener());
	        
	        int channel = Integer.parseInt(in.readLine());
	        observer.notify(channel, lobby, out, in, clientSocket);
	        
            in.close();
            out.close();
            clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

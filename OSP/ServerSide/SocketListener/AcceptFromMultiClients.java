package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import OSP.ServerSide.Engine.Lobby;

public class AcceptFromMultiClients extends Thread {

	private Lobby lobby;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Random rand = new Random();

    public AcceptFromMultiClients(Socket socket, Lobby lobby) {
        this.clientSocket = socket;
        this.lobby = lobby;
    }

    public void run() {
        try {
	        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
	        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        
	        int channel = Integer.parseInt(in.readLine());
	        switch (channel) {
		        case -1: {
		        	testConnection();
		        	break;
		        }
				case 0: {
					newUserJoined();
					break;
				}
				case 1: {
					String ID = in.readLine();
					lobby.keepAlive(ID);
					break;
				}
				case 2: {
					updateInformation();
					break;
				}
				case 3: {
					sendFirstInformation();
					break;
				}
				case 10: {
					getPlayerPositions();
					break;
				}
				case 11: {
					updatePlayerPosition();
					break;
				}
				case 12: {
					getBombs();
					break;
				}
				case 13: {
					placeBomb();
					break;
				}
				case 14: {
					updateMap();
					break;
				}
				case 20: {
					updateEnd();
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + channel);
			}
	        
            in.close();
            out.close();
            clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void updateEnd() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID)) {
			lobby.keepAlive(ID);
			out.println(lobby.hasGameStarted());
		}
	}

	private void updateMap() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
			lobby.keepAlive(ID);
			out.println(lobby.getGame().getMap());
		}
	}

	private void getBombs() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
			lobby.keepAlive(ID);
			out.println(lobby.getGame().getBombStatus());
		}
	}

	private void placeBomb() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
			lobby.keepAlive(ID);
			int x = Integer.valueOf(in.readLine());
			int y = Integer.valueOf(in.readLine());
			lobby.getGame().addBomb(ID, x, y);
		}
	}

	private void updatePlayerPosition() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
			lobby.keepAlive(ID);
			int x = Integer.valueOf(in.readLine());
			int y = Integer.valueOf(in.readLine());
			lobby.getPlayer(ID).getLocation().setLocation(x, y);;
		}
	}

	private void getPlayerPositions() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
			lobby.keepAlive(ID);
			out.println(lobby.getGame().getPlayerStatus());
		}
	}

	private void sendFirstInformation() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID) && lobby.hasGameStarted()) {
			lobby.keepAlive(ID);
			out.println(3);
			out.println(lobby.getGame().getMap());
			out.println(lobby.getGame().getPlayerStatus());
		}
	}

	private void updateInformation() throws IOException {
		String ID = in.readLine();
		if (lobby.containsPlayer(ID)) {
			if (lobby.getPlayers().size() == 1) {
				out.println(0); // Waiting for players
			}
			if (lobby.getPlayers().size() > 1 && !lobby.hasGameStarted()) {
				out.println(1); // Strting match and waiting for players
				out.println(lobby.getCountDown());
				out.println(lobby.getPlayers().size());
			}
			if (lobby.hasGameStarted()) {
				out.println(2);
			}
		}
	}

	private void newUserJoined() {
		if (lobby.getPlayers().size() == 4) {
			System.out.println("User was unable to join because the server is full; IP: " + this.clientSocket.getInetAddress().getHostAddress());
			return;
		}
		int ID = rand.nextInt(9000) + 1000;
		lobby.addPlayer(String.valueOf(ID));
		out.println(ID);
		System.out.println("User joined with ID: " + ID + "; IP: " + this.clientSocket.getInetAddress().getHostAddress());
    }
    
    private void testConnection() throws IOException {
		out.println("Messages was received!");
		System.out.println("Message was receved from " + this.clientSocket.getInetAddress().getHostAddress()+":");
		String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	System.out.println(inputLine);
        }
    }
}

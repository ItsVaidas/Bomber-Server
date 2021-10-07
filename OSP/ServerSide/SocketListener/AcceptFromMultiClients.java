package OSP.ServerSide.SocketListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import OSP.ServerSide.PlayerData;

public class AcceptFromMultiClients extends Thread {
	
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Random rand = new Random();

    public AcceptFromMultiClients(Socket socket) {
        this.clientSocket = socket;
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
					int ID = Integer.parseInt(in.readLine());
					PlayerData.updateUser(ID);
					break;
				}
				case 2: {
					updateInformation();
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
    
    private void updateInformation() throws IOException {
		//int ID = Integer.parseInt(in.readLine());
		if (PlayerData.getUsers().size() == 1) {
			out.println(0); // Waiting for players
		}
		if (PlayerData.getUsers().size() > 1) {
			out.println(1); // Strting match and waiting for players
			out.println(30);
			out.println(PlayerData.getUsers().size());
		}
	}

	private void newUserJoined() {
		if (PlayerData.getUsers().size() == 4) {
			System.out.println("User was unable to join because the server is full; IP: " + this.clientSocket.getInetAddress().getHostAddress());
			return;
		}
		int ID = rand.nextInt(9000) + 1000;
		PlayerData.addUser(ID);
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

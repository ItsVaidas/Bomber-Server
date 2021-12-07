package OSP.ServerSide;

import java.util.Scanner;

import OSP.Interpreter.*;
import OSP.ServerSide.Engine.Lobby;
import OSP.ServerSide.Objects.Player;
import OSP.ServerSide.SocketListener.TCPPortListenerFacade;

public class Main {
	
	private static TCPPortListenerFacade tcpPortListenerFacade;
	private static Lobby lobby;

	public static void main(String[] args) {
		lobby = new Lobby();
		
		startListener();
		checkForCommand();
		
	}
	
	private static void checkForCommand() {
		GlobalPlayerStatsContext stats = new GlobalPlayerStatsContext();
		
		while(true) {
			Scanner in = new Scanner(System.in);
	        String s = in.nextLine();
	        String[] info = s.split("=");
	        String statName = info[0];
	        String statQuantity = info[1];
	        
	        PlayerStatsExpression phe = new PlayerStatsExpression(
					new TerminalExpression(statName),
					new TerminalExpression(statQuantity)
			);

	        phe.interpret(stats);
			
			for(Player player : lobby.getPlayers()) {
				player.addHealth(stats.getHealth());
				player.addDamage(stats.getDamage());
				player.addSpeed(stats.getSpeed());
			}

	        stats.clean();
			
		}
	}
	
	private static void startListener() {
		tcpPortListenerFacade = new TCPPortListenerFacade(lobby, new Player());
		tcpPortListenerFacade.start(11111);
	}
}

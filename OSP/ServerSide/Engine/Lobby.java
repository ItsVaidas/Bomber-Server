package OSP.ServerSide.Engine;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import OSP.ServerSide.Objects.Player;

public class Lobby {
	
	List<Player> players;
	Timer countdown;
	Timer checkForWiner;
	int secondsTillStart;
	
	Game game;
	
	public Lobby() {
		players = new ArrayList<>();
		
		checkForWiner();
	}
	
	private void checkForWiner() {
		checkForWiner = new Timer(50, (e) -> {
			if (!hasGameStarted()) return;
			int i = 0;
			for (Player p : getPlayers())
				if (!p.isDead())
					i++;
			if (i == 1) {
				game = null;
				startCountdown();
			}
		});
		checkForWiner.start();
	}

	private void startCountdown() {
		if (countdown != null) return;
		secondsTillStart = 30;
		countdown = new Timer(1000, (e) -> {
			secondsTillStart--;
			if (secondsTillStart == 0) {
				startGame();
			}
		});
		countdown.start();
	}
	
	private void startGame() {
		if (countdown != null) {
			countdown.stop();
			countdown = null;
		}
		game = Game.getGameInstance(this);
	}

	public void addPlayer(String ID) {
		players.add(new Player(ID));
		if (getPlayers().size() >= 2) {
			startCountdown();
		}
	}
	
	public void removePlayer(String ID) {
		for (Player p : getPlayers())
			if (p.getID().equals(ID))
				players.remove(p);
		if (getPlayers().size() <= 1) {
			if (countdown != null) {
				countdown.stop();
				countdown = null;
			}
			secondsTillStart = 30;
		}
	}
	
	public List<Player> getPlayers() {
		return new ArrayList<>(players);
	}

	public void keepAlive(String ID) {
		for (Player p : getPlayers())
			if (p.getID().equals(ID))
				p.keepAlive();
	}

	public boolean containsPlayer(String ID) {
		for (Player p : getPlayers())
			if (p.getID().equals(ID))
				return true;
		return false;
	}
	
	public int getCountDown() {
		return secondsTillStart;
	}

	public boolean hasGameStarted() {
		return game != null;
	}

	public Game getGame() {
		return game;
	}

	public Player getPlayer(String ID) {
		for (Player p : getPlayers())
			if (p.getID().equals(ID))
				return p;
		return null;
	}

}

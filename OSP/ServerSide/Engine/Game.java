package OSP.ServerSide.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Timer;

import OSP.ServerSide.Objects.Bomb;
import OSP.ServerSide.Objects.Location;
import OSP.ServerSide.Objects.Map;
import OSP.ServerSide.Objects.Player;

public class Game {

	Lobby lobby;
	Map map;
	List<Bomb> bombs;
	List<int[]> initialLocations = Arrays.asList(new int[] {1, 1}, new int[] {1, 8}, new int[] {8, 1}, new int[] {8, 8});
	
	public Game(Lobby lobby) {
		this.lobby = lobby;
		this.map = new Map(this);
		bombs = new ArrayList<>();
		
		int i = 0;
		for (Player p : lobby.getPlayers()) {
			int[] xy = initialLocations.get(i++);
			p.getLocation().setLocation(xy[1], xy[0]);
			p.revive();
		}
		
		explodeBombs();
	}

	public void addBomb(String ID, int x, int y) {
		bombs.add(new Bomb(lobby.getPlayer(ID), new Location(x, y)));
	}

	public List<Bomb> getBombs() {
		return new ArrayList<>(bombs);
	}

	private void explodeBombs() {
		new Timer(50, (e) -> {
			for (Bomb b : getBombs()) {
				if (b.isExploded()) {
					map.doExplosionToBlocks(b);
					this.bombs.remove(b);
				}
			}
		}).start();
	}

	public String getMap() {
		return map.toString();
	}

	public String getPlayerStatus() {
		String toReturn = null;
		for (Player p : lobby.getPlayers())
			if (toReturn == null)
				toReturn = p.toString();
			else
				toReturn += "-" + p.toString();
		
		return toReturn;
	}
	
	public String getBombStatus() {
		String toReturn = null;
		for (Bomb b : getBombs())
			if (toReturn == null)
				toReturn = b.toString();
			else
				toReturn += "-" + b.toString();
		return toReturn;
	}

	public List<Player> getPlayers() {
		return lobby.getPlayers();
	}
}

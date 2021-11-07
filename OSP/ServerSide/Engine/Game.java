package OSP.ServerSide.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import java.util.Iterator;

import OSP.ServerSide.Objects.Bomb;
import OSP.ServerSide.Objects.Fruit;
import OSP.ServerSide.Objects.HealthPowerUpAlgorithm;
import OSP.ServerSide.Objects.Location;
import OSP.ServerSide.Objects.Map;
import OSP.ServerSide.Objects.Player;
import OSP.ServerSide.Objects.PowerUp;

public class Game {

	Lobby lobby;
	Map map;
	List<Bomb> bombs;
	List<PowerUp> powerUps;
	public int aa = 0;
	List<int[]> initialLocations = Arrays.asList(new int[] {1, 1}, new int[] {1, 8}, new int[] {8, 1}, new int[] {8, 8});
	private static Game gameInstance = null;
	private Game(Lobby lobby) {
		this.lobby = lobby;
		this.map = new Map(this);
		bombs = new ArrayList<>();
		powerUps = new ArrayList<>();
		
		int i = 0;
		for (Player p : lobby.getPlayers()) {
			int[] xy = initialLocations.get(i++);
			p.getLocation().setLocation(xy[1], xy[0]);
			p.revive();
		}
		
		explodeBombs();
		checkPowerUps();
		addPowerUps();
	}
	
	public synchronized static Game getGameInstance(Lobby lobby) {
		if(gameInstance==null) {
			gameInstance = new Game(lobby);
		}
		return gameInstance;
	}
	
	public void addBomb(String ID, int x, int y) {
		bombs.add(new Bomb(lobby.getPlayer(ID), new Location(x, y)));
	}
	
	public void addPowerUps() {
		Random r = new Random();
		
		for(int i = 0; i < 2; i++) {
			int randomPosY = r.nextInt((map.getHeight() - 2) - 2) + 2;
			int randomPosX = r.nextInt((map.getWidth() - 2) - 2) + 2;
			Location randomLocation = new Location(randomPosX, randomPosY);
			Fruit fruit = new Fruit(randomLocation);
			fruit.setPowerUpAlgorithm(new HealthPowerUpAlgorithm());
			this.powerUps.add(fruit);
		}
	}

	public void removeBomb(String ID, int x, int y) {
		for (Bomb b : getBombs()) {
			if(b.removeB()) {
				//this.bombs.clear();
			}
		}
		this.bombs.clear();
		//System.out.println("removed");
	}
	public List<Bomb> getBombs() {
		return new ArrayList<>(bombs);
	}
	
	public List<PowerUp> getPowerUps() {
		return new ArrayList<>(powerUps);
	}
	
	public void removePowerUp(PowerUp powerUp) {
		this.powerUps.remove(powerUp);
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
	
	private void checkPowerUps() {
		new Timer(50, (e) -> {			
			for(Iterator<PowerUp> itr = this.powerUps.iterator(); itr.hasNext();) {
				PowerUp p = itr.next();
				checkPowerUp(p, itr);
			}
		}).start();
	}
	
	public void checkPowerUp(PowerUp powerUp, Iterator<PowerUp> itr) {
		int x = powerUp.getLocation().X();
		int y = powerUp.getLocation().Y();	
		
		for (Player p : getPlayers()) {
			if (p.getLocation().X() == x && p.getLocation().Y() == y) {
				powerUp.executePowerUpAlgorithm(p);
				itr.remove();
			}	
		}
	}

	public String getMap() {
		return map.toString();
	}
	
	public String getPowerUpsToString() { 
		String toReturn = null;
		for (PowerUp powerUp : this.powerUps)
			if (toReturn == null)
				toReturn = powerUp.toString();
			else
				toReturn += "-" + powerUp.toString();
		
		return toReturn;
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

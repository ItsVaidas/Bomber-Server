package OSP.ServerSide.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import Collections.PowerUpCollection;

import java.util.Iterator;

import OSP.ServerSide.Objects.*;
import Services.*;


public class Game {

	Lobby lobby;
	Map map;
	List<Bomb> bombs;
	List<PowerUp> powerUps = new ArrayList<PowerUp>();
	List<int[]> initialLocations = Arrays.asList(new int[] {1, 1}, new int[] {1, 8}, new int[] {8, 1}, new int[] {8, 8});
	
	private static Game gameInstance = null;
	private Game(Lobby lobby) {
		this.lobby = lobby;
		
		Director director = new Director();
		this.map = director.createMap(new MapBuilder());
		
		bombs = new ArrayList<>();
		
		int i = 0;
		for (Player p : lobby.getPlayers()) {
			int[] xy = initialLocations.get(i++);
			p.getLocation().setLocation(xy[1], xy[0]);
			p.revive();
		}
		
		explodeBombs();
		addPowerUps();
		checkPowerUps();
	}

	public void newGame() {
		
		Director director = new Director();
		this.map = director.createMap(new MapBuilder());
		
		bombs = new ArrayList<>();
		
		int i = 0;
		for (Player p : lobby.getPlayers()) {
			int[] xy = initialLocations.get(i++);
			p.getLocation().setLocation(xy[1], xy[0]);
			p.revive();
		}
		
		explodeBombs();
		addPowerUps();
		checkPowerUps();
	}
	
	public void checkPowerUps() {
		new Timer(50, (e) -> {			
			for(Iterator<PowerUp> itr = this.powerUps.iterator(); itr.hasNext();) {
				PowerUp p = itr.next();
				new PlayerLevelServiceProxy(new PowerUpCheckService(), 1).checkPowerUp(p, itr, getPlayers());
			}
		}).start();
	}
	
	private void addPowerUps() {
		PowerUpIterator powerUpIterator = new PowerUpCollection().createPowerUpIterator();
		
		while (powerUpIterator.hasNext()) {
			this.powerUps.add(powerUpIterator.getNext());
		}	
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
					System.out.println("Explosion");
					map.doExplosionToBlocks(b, this.getPlayers());
					this.bombs.remove(b);
				}
			}
		}).start();
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

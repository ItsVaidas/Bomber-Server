package OSP.ServerSide.Objects;

import OSP.ServerSide.Engine.Game;

public class Map {
	int[][] map;
	int height;
	int width;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		map = new int[width][height];
	}

	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int[][] newMap) {
		this.map = newMap;
	}
	
	public int getHeight() {
		return this.width;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				builder.append(map[y][x]);
			}
		return builder.toString();
	}
	
	public void doExplosionToBlocks(Bomb b, Game game) {
		int x = b.getLocation().X();
		int y = b.getLocation().Y();
		
		int from = 3;
		int to = 3;
		
		for (int i = y - from < 0 ? 0 : y - from; i <= y + to && i < this.height; i++) {
			if (map[i][x] == 1)
				map[i][x] = 0;
			for (Player p : game.getPlayers())
				if (p.getLocation().X() == x && p.getLocation().Y() == i)
					p.died();
		}
		for (int j = x - from < 0 ? 0 : x - from; j <= x + to && j < this.width; j++) {
			if (map[y][j] == 1)
				map[y][j] = 0;
			for (Player p : game.getPlayers())
				if (p.getLocation().X() == j && p.getLocation().Y() == y)
					p.died();
		}
	}
}

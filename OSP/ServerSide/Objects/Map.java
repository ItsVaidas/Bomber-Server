package OSP.ServerSide.Objects;

import java.util.Random;

import OSP.ServerSide.Engine.Game;

public class Map {
	
	Random r;
	int WIDTH = 10;
	int HEIGHT = 10;
	int[][] map;
	Game game;
	
	public Map(Game game) {
		this.game = game;
		map = new int[WIDTH][HEIGHT];
		r = new Random();
		
		generateNewMap();
		generateSpawnZones();
	}

	private void generateNewMap() {
		for (int i = 0; i < WIDTH; i++) {
			map[0][i] = 2;
			map[9][i] = 2;
		}
		for (int i = 0; i < HEIGHT; i++) {
			map[i][0] = 2;
			map[i][9] = 2;
		}
		for (int y = 1; y < HEIGHT - 1; y++)
			for (int x = 1; x < HEIGHT - 1; x++) {
				map[y][x] = r.nextInt(3);
			}
	}

	private void generateSpawnZones() {
		//First player
		map[1][1] = 0;
		map[1][2] = 0;
		map[2][1] = 0;

		//Second player
		map[1][7] = 0;
		map[1][8] = 0;
		map[2][8] = 0;
		
		//Third player
		map[7][1] = 0;
		map[8][2] = 0;
		map[8][1] = 0;

		//Forth player
		map[8][7] = 0;
		map[8][8] = 0;
		map[7][8] = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < HEIGHT; y++)
			for (int x = 0; x < HEIGHT; x++) {
				builder.append(map[y][x]);
			}
		return builder.toString();
	}
	
	public void doExplosionToBlocks(Bomb b) {
		int x = b.getLocation().X();
		int y = b.getLocation().Y();
		
		int from = 3;
		int to = 3;
		
		for (int i = y - from < 0 ? 0 : y - from; i <= y + to && i < HEIGHT; i++) {
			if (map[i][x] == 1)
				map[i][x] = 0;
			for (Player p : game.getPlayers())
				if (p.getLocation().X() == x && p.getLocation().Y() == i)
					p.died();
		}
		for (int j = x - from < 0 ? 0 : x - from; j <= x + to && j < WIDTH; j++) {
			if (map[y][j] == 1)
				map[y][j] = 0;
			for (Player p : game.getPlayers())
				if (p.getLocation().X() == j && p.getLocation().Y() == y)
					p.died();
		}
	}
	
	

}

package OSP.ServerSide.Objects;

import java.util.Random;

public class MapBuilder implements IBuilder {

	Map map;
	int height = 10;
	int width = 10;
	Random r;
	
	public MapBuilder() {
	}
	
	public void setDimensions(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	public void createMap() {
		this.map = new Map(this.height, this.width);
	}
	
	public void addBoundaryWalls() {
		int[][] map = this.map.getMap();
		
		for (int i = 0; i < width; i++) {
			map[0][i] = 2;
			map[9][i] = 2;
		}
		for (int i = 0; i < height; i++) {
			map[i][0] = 2;
			map[i][9] = 2;
		}
		
		this.map.setMap(map);
	}
	
	public void addInnerMapTiles() {
		this.r = new Random();
		int[][] map = this.map.getMap();
		
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				int random = r.nextInt(3);
				if(random == 2) {
					random = 1;
				}
				map[y][x] = random;	
			}	
		}
		
		this.map.setMap(map);
	}
	
	public void addSpawnZones() {
		int[][] map = this.map.getMap();
		
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

		this.map.setMap(map);
	}
	
	public Map getProduct() {
		return this.map;
	}

	
	@Override
	public void reset() {
		map = null;
	}
	
}

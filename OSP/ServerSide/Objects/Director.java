package OSP.ServerSide.Objects;

public class Director {
	public Map createMap(MapBuilder mapBuilder) {
		mapBuilder.createMap();
		mapBuilder.addBoundaryWalls();
		mapBuilder.addInnerMapTiles();
		mapBuilder.addSpawnZones();
		
		return mapBuilder.getProduct();
	}
}

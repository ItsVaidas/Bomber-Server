package OSP.ServerSide.Objects;

public class Location {
	
	int x;
	int y;
	
	public Location() {
	}
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int X() {
		return this.x;
	}
	
	public int Y() {
		return this.y;
	}
}

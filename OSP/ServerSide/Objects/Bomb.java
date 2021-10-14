package OSP.ServerSide.Objects;

public class Bomb {
	
	Location l;
	long explodeIn;
	Player p;

	public Bomb(Player p, Location l) {
		this.l = l;
		this.p = p;
		explodeIn = System.currentTimeMillis() + 4000;
	}
	
	public Location getLocation() {
		return this.l;
	}

	public boolean isExploded() {
		return explodeIn < System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		return l.X()+"/"+l.Y()+" "+explodeIn+" "+p.getID();
	}
}

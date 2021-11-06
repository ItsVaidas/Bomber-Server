package OSP.ServerSide.Objects;

public class Bomb {
	
	Location l;
	long explodeIn;
	Player p;
    int removed;
	public Bomb(Player p, Location l) {
		this.l = l;
		this.p = p;
		explodeIn = System.currentTimeMillis() + 4000;
		removed = 0;
	}
	
	public Location getLocation() {
		return this.l;
	}

	public boolean isExploded() {
		return explodeIn < System.currentTimeMillis();
	}
	 public boolean removeB() {
		 removed = 1;
		 return true;
	 }
	
	
	@Override
	public String toString() {
		return l.X()+"/"+l.Y()+" "+explodeIn+" "+p.getID()+" "+removed;
	}
}

package OSP.ServerSide.Objects;

public class Player {
	
	String ID;
	long timeout;
	Location l;
	boolean isDead;
	
	public Player(String ID) {
		this.ID = ID;
		this.timeout = System.currentTimeMillis() + 100;
		isDead = false;
		
		l = new Location();
	}

	public boolean isDead() {
		return isDead;
	}
	
	public void died() {
		isDead = true;
	}

	public String getID() {
		return this.ID;
	}
	
	public long getTimeOut() {
		return this.timeout;
	}

	public void keepAlive() {
		this.timeout = System.currentTimeMillis() + 100;
	}

	public Location getLocation() {
		return l;
	}
	
	@Override
	public String toString() {
		return ID + " " + l.X() + "/" + l.Y() + " " + isDead;
	}

	public void revive() {
		isDead = false;
	}

}

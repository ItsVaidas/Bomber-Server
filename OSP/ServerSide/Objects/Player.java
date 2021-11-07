package OSP.ServerSide.Objects;

public class Player {
	
	String ID;
	long timeout;
	Location l;
	boolean isDead;
	int health = 1;
	
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
		health--;
		if(health == 1) {
			isDead = true;	
		}
	}
	
	public void addHealth(int health) {
		this.health += health;
	}

	public String getID() {
		return this.ID;
	}
	
	public long getTimeOut() {
		return this.timeout;
	}

	public void keepAlive() {
		this.timeout = System.currentTimeMillis() + 1000;
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

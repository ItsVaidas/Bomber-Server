package OSP.ServerSide.Objects;

public class Player {
	
	String ID;
	long timeout;
	Location l;
	boolean isDead;
	int health = 1;
	int power = 1;
	int speed = 1;
	int damage = 1;
	
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
	
	public void addDamage(int damage) {
		this.damage += damage;
	}
	
	public void addSpeed(int speed) {
		this.speed += speed;
	}
	
	public void addHealth(int health) {
		this.health += health;
	}
	
	public void addPower(int power) {
		this.power += power;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void removeHealth(int health) {
		this.health -= health;
		if(this.health == 0) {
			this.died();
		}
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
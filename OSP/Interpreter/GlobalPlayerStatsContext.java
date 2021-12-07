package OSP.Interpreter;

public class GlobalPlayerStatsContext {
	private int health;
	private int damage;
	private int speed;
	
	public void updateStats(String statName, String quantity) {
		int formattedQuantity = Integer.parseInt(quantity);
		
		switch(statName) {
		case "health": 
			this.health = formattedQuantity; 
			break;
		case "damage": 
			this.damage = formattedQuantity;
			break;
		case "speed":
			this.speed = formattedQuantity;
			break;
		default: 
			System.out.println("Warning: Command not supported");
			break;
		}
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void clean() {
		this.health = 0;
		this.damage = 0;
		this.speed = 0;
	}
}

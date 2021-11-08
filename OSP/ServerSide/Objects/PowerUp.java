package OSP.ServerSide.Objects;

public class PowerUp {
	Location l;
	int type;
	PowerUpAlgorithm powerUpAlgorithm;
	
	public PowerUp(Location l, int type) {
		this.l = l;
		this.type = type;
	}
	
	public Location getLocation() {
		return this.l;
	}
	
	public void executePowerUpAlgorithm(Player player) {
		powerUpAlgorithm.executePowerUp(player);
	}
	
	public void setPowerUpAlgorithm(PowerUpAlgorithm algorithm) {
		this.powerUpAlgorithm = algorithm;
	}

	@Override
	public String toString() {
		return l.X()+"/"+l.Y()+" "+type;
	}
}
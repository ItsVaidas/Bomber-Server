package OSP.ServerSide.Objects;

public class DamagePowerUpDecorator extends Decorator {
	public DamagePowerUpDecorator(Player p) {
		super(p);
	}
	
	public int getDamage() {
		return player.getDamage();
	}

}

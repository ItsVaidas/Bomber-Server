package OSP.ServerSide.Objects;

public class SpeedPowerUpDecorator extends Decorator {
	public SpeedPowerUpDecorator(Player player) {
		super(player);
	}
	
	public int getSpeed() {
		return player.getSpeed();
	}
}

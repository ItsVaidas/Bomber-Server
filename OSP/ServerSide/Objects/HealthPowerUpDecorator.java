package OSP.ServerSide.Objects;

public class HealthPowerUpDecorator extends Decorator {
	
	public HealthPowerUpDecorator(Player player) {
        super(player);
    }

    public int getHealth( )
	{
		return player.getHealth();
	}
}

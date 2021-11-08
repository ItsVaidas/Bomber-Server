package OSP.ServerSide.Objects;

public class SpeedPowerUpAlgorithm extends PowerUpAlgorithm {

	@Override
	public void executePowerUp(Player player) {
		player.addSpeed(1);
		System.out.println("Speed added to user: "+player.ID);
	}
	
}

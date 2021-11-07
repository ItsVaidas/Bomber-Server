package OSP.ServerSide.Objects;

public class HealthPowerUpAlgorithm extends PowerUpAlgorithm {

	@Override
	public void executePowerUp(Player player) {
		player.addHealth(1);
		System.out.println("Health added to user: "+player.ID);
	}
	
}

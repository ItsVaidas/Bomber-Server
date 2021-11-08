package OSP.ServerSide.Objects;

public class PoisonPowerUpAlgorithm extends PowerUpAlgorithm {

	@Override
	public void executePowerUp(Player player) {
		player.removeHealth(1);
		System.out.println("Health removed to user: "+player.ID);
	}
	
}

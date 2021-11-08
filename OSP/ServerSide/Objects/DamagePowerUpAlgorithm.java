package OSP.ServerSide.Objects;

public class DamagePowerUpAlgorithm extends PowerUpAlgorithm {

	@Override
	public void executePowerUp(Player player) {
		player.addDamage(1);
		System.out.println("Damage added to user: "+player.ID);
	}
	
}

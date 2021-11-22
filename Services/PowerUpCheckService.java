package Services;

import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import OSP.ServerSide.Objects.DamagePowerUpDecorator;
import OSP.ServerSide.Objects.HealthPowerUpDecorator;
import OSP.ServerSide.Objects.Player;
import OSP.ServerSide.Objects.PowerUp;
import OSP.ServerSide.Objects.SpeedPowerUpDecorator;

public class PowerUpCheckService implements IPowerUpCheckService {	
	public void checkPowerUp(PowerUp powerUp, Iterator<PowerUp> itr, List<Player> players) {
		int x = powerUp.getLocation().X();
		int y = powerUp.getLocation().Y();	
		
		for (Player p : players) {
			if (p.getLocation().X() == x && p.getLocation().Y() == y) {
				Player healthDecorator = new HealthPowerUpDecorator(p);
				Player speedAndHealthDecorator = new SpeedPowerUpDecorator(healthDecorator);
				Player mainDecorator = new DamagePowerUpDecorator(speedAndHealthDecorator);
				powerUp.executePowerUpAlgorithm(p);
				System.out.println("User(ID:"+p.getID()+") health:" + mainDecorator.getHealth() + " speed:" + mainDecorator.getSpeed()+ " damage:"+ mainDecorator.getDamage());
				itr.remove();
			}	
		}
	}
	
}

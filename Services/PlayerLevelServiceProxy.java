package Services;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import OSP.ServerSide.Objects.Player;
import OSP.ServerSide.Objects.PowerUp;

public class PlayerLevelServiceProxy implements IPowerUpCheckService {

	IPowerUpCheckService powerUpCheckService;
	int requiredLevel;
	
	public PlayerLevelServiceProxy(IPowerUpCheckService powerUpCheckService, int requiredLevel) {
		this.powerUpCheckService = powerUpCheckService;
		this.requiredLevel = requiredLevel;
	}
	
	public List<Player> filterPlayerLevel(List<Player> players) {
		return players.stream().filter(p -> p.getLevel() == this.requiredLevel).collect(Collectors.toList());
	}

	@Override
	public void checkPowerUp(PowerUp powerUp, Iterator<PowerUp> itr, List<Player> players) {
		powerUpCheckService.checkPowerUp(powerUp, itr, filterPlayerLevel(players));
	}	
}

package Services;

import java.util.Iterator;
import java.util.List;

import OSP.ServerSide.Objects.Player;
import OSP.ServerSide.Objects.PowerUp;

public interface IPowerUpCheckService {
	public void checkPowerUp(PowerUp powerUp, Iterator<PowerUp> itr, List<Player> players);
}

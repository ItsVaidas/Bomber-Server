package OSP.ServerSide.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpIterator implements IPowerUpIterator {
	List<PowerUp> powerUps;
	int currentPosition = 0;
	
	private void lazyInit() {
		if(powerUps == null) {
			this.powerUps = new ArrayList<PowerUp>();
			List<Location> locations = new ArrayList<>();
			
			for(int i = 0; i < 2; i++) {
				Location randomLocation = generateFreeRandomLocation(locations);
				locations.add(randomLocation);
				Fruit fruit = new Fruit(randomLocation);
				fruit.setPowerUpAlgorithm(new HealthPowerUpAlgorithm());
				this.powerUps.add(fruit);
			}
			
			for(int i = 0; i < 2; i++) {
				Location randomLocation = generateFreeRandomLocation(locations);
				locations.add(randomLocation);
				Poison poison = new Poison(randomLocation);
				poison.setPowerUpAlgorithm(new PoisonPowerUpAlgorithm());
				this.powerUps.add(poison);
			}
			
			for(int i = 0; i < 2; i++) {
				Location randomLocation = generateFreeRandomLocation(locations);
				locations.add(randomLocation);
				SpeedPoition poition = new SpeedPoition(randomLocation);
				poition.setPowerUpAlgorithm(new SpeedPowerUpAlgorithm());
				this.powerUps.add(poition);
			}
			
			for(int i = 0; i < 2; i++) {
				Location randomLocation = generateFreeRandomLocation(locations);
				locations.add(randomLocation);
				Sword sword = new Sword(randomLocation);
				sword.setPowerUpAlgorithm(new DamagePowerUpAlgorithm());
				this.powerUps.add(sword);
			}	
		}
	}
	
	public Location generateFreeRandomLocation(List<Location> locations) {
		boolean isFound = false;
		Random r = new Random();
		Location newLocation = new Location(0, 0);
		while(!isFound) {
			int randomPosY = r.nextInt(6) + 2;
			int randomPosX = r.nextInt(6) + 2;
			Location randomLocation = new Location(randomPosX, randomPosY);
			if(!locations.contains(randomLocation)) {
				newLocation = randomLocation;
				isFound = true;
			}
		}
		
		return newLocation;
	}
	
	@Override
	public PowerUp getNext() {
		if(hasNext()) {
			currentPosition++;
			return this.powerUps.get(currentPosition);
		}
		
		return null;
	}

	@Override
	public boolean hasNext() {
		lazyInit();
		return currentPosition < this.powerUps.size() - 1;
	}
	
}

package OSP.ServerSide.Objects;


public class Decorator extends Player {
    
	protected Player player;
	
	public Decorator( Player component )
	{
		super(component.getID(), component.getLevel());
		player = component;
	}
	

	public int getHealth( )
	{
		return player.getHealth();
	}
	
	public int getPower() {
		return player.getPower();
	}
	
	public int getSpeed() {
		return player.getSpeed();
	}
	
	public int getDamage() {
		return player.getDamage();
	}
}

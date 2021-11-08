package OSP.ServerSide.Objects;


public class Decorator extends Player {
    
	protected Player player;
	
	public Decorator( Player component )
	{
		super(component.getID());
		player = component;
	}
	

	public int getHealth( )
	{
		return player.getHealth();
	}
	
}

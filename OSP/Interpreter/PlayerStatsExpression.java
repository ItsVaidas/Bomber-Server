package OSP.Interpreter;

import OSP.ServerSide.Engine.Lobby;

public class PlayerStatsExpression implements Expression {

	private Expression statNameExpression;
	private Expression quantityExpression;
	
	public PlayerStatsExpression(Expression stat, Expression quan) {
		this.quantityExpression = quan;
		this.statNameExpression = stat;
	}

	@Override
	public String interpret(GlobalPlayerStatsContext context) {
		context.updateStats(
				this.statNameExpression.interpret(context), 
				this.quantityExpression.interpret(context)
		);
		return null;
	}
	
}

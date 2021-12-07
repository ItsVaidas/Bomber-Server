package OSP.Interpreter;

import OSP.ServerSide.Engine.Lobby;

public interface Expression {
	public String interpret(GlobalPlayerStatsContext context);
}

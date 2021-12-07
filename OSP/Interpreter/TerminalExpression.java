package OSP.Interpreter;

import OSP.ServerSide.Engine.Lobby;

public class TerminalExpression implements Expression {
	String input;   
	
	public TerminalExpression(String input) {
		this.input = input;
	}
	
	@Override
	public String interpret(GlobalPlayerStatsContext context) {
		return this.input;
	}
}

package hw1;

public class Var implements MathExpression {

	private int id;
	
	private MathExpression previouslyMatched = null;
	
	public Var(int id) { this.id = id; }
	
	public int getId() { return this.id; }
	
	public MathExpression getPreviousMatch() { return this.previouslyMatched; }
	
	public void setPreviousMatch(MathExpression me) { this.previouslyMatched = me; }
	
	@Override
	public <T> T accept(MathVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean match(MathExpression me) {
		if(this.previouslyMatched == null) {
			this.previouslyMatched = me;
			return true;
		}
		else {
			boolean result = me.match(this.previouslyMatched);
			return result;
		}
		
		
	}
	
}

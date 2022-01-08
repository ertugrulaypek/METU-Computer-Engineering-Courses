package hw1;

public class XDotYDivXIsYRule implements Rule {

	private Var x;
	private Var y;
	
	public XDotYDivXIsYRule(Var x, Var y) {
		this.x = x;
		this.y = y;
	}
	
	public Var getX() { return this.x; }
	
	public Var getY() { return this.y; }
	
	@Override
	public void clear() {
		this.x.setPreviousMatch(null);
		this.y.setPreviousMatch(null);
	}

	@Override
	public MathExpression getPremise() {
		return new Op("/", new Op("*", this.x, this.y), this.x);
	}

	@Override
	public MathExpression getEntails() {
		return this.y;
	}

}

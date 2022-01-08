package hw1;

public class XDotZeroIsZeroRule implements Rule {

	private Var x;
	
	public XDotZeroIsZeroRule(Var x) {
		this.x = x;
	}
	
	public Var getX() { return this.x;}
	
	
	@Override
	public void clear() {
		this.x.setPreviousMatch(null);
	}

	@Override
	public MathExpression getPremise() {
		return new Op("*", this.x, new Num(0));
	}

	@Override
	public MathExpression getEntails() {
		return new Num(0);
	}

}

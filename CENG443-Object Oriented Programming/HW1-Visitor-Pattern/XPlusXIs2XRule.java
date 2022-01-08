package hw1;

public class XPlusXIs2XRule implements Rule {
	
	private Var x;
	
	public XPlusXIs2XRule(Var x){
		this.x = x;
	}
		
	Var getX() {return this.x; }
	
	@Override
	public void clear() {
		this.x.setPreviousMatch(null);
	}

	@Override
	public MathExpression getPremise() {
		
		return new Op("+", this.x, this.x);
	}

	@Override
	public MathExpression getEntails() {
		return new Op("*", new Num(2), this.x);
	}

}

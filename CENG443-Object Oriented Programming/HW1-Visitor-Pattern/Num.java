package hw1;

public class Num implements MathExpression {

	private int value;
	
	@Override
	public <T> T accept(MathVisitor<T> visitor) {
		return visitor.visit(this);
	}
	 
	public Num(int value) { this.value = value; }

	public int getValue() { return this.value; }
	
	@Override
	public boolean match(MathExpression me) {
		if(me instanceof Num) {
			Num nm = (Num) me;
			if(nm.getValue() == this.value) {return true;}
			return false;
		}
		else return false;
	}
}

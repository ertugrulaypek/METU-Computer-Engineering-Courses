package hw1;

public class EquationText implements DocElement {

	private MathExpression me;
	
	public EquationText(MathExpression innerMath) {
		this.me = innerMath;
	}
	
	public MathExpression getInnerMath() {
		return this.me;
	}
	
	@Override
	public <T> T accept(TextVisitor<T> visitor) {
		return visitor.visit(this);
	}

}

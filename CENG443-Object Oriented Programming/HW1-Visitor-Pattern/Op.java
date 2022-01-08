package hw1;

public class Op implements MathExpression {

	private String operand;
	private MathExpression first;
	private MathExpression second;
	
	@Override
	public <T> T accept(MathVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean match(MathExpression me) {
		if(me instanceof Op) {
			Op op = (Op) me;
			if(op.getOperand() == this.operand) return this.first.match(op.getFirst()) && this.second.match(op.getSecond());
			else return false;
		}
		else return false;
	}
	
	public Op(String operand,MathExpression first,MathExpression second) {
		this.operand = operand;
		this.first = first;
		this.second = second;
	}
	
	public String getOperand() { return this.operand; }
	
	public MathExpression getFirst() { return this.first; }
	
	public MathExpression getSecond() { return this.second; }
	
}

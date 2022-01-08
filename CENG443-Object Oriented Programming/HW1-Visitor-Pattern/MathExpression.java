package hw1;

public interface MathExpression {
	public abstract <T> T accept(MathVisitor<T> visitor);
	public abstract boolean match(MathExpression me);
}

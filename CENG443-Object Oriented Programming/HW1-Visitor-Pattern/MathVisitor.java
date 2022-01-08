package hw1;

public interface MathVisitor<T> {
	public abstract T visit(Op op);

	public abstract T visit(Var var);

	public abstract T visit(Num num);

	public abstract T visit(Sym sym);
}

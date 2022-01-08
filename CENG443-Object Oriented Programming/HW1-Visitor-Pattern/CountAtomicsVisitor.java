package hw1;

public class CountAtomicsVisitor implements MathVisitor<Integer> {

	@Override
	public Integer visit(Op op) {
		MathExpression first = op.getFirst();
		MathExpression second = op.getSecond();
		return first.accept(this) + second.accept(this);
	}

	@Override
	public Integer visit(Var var) {
		return 1;
	}

	@Override
	public Integer visit(Num num) {
		return 1;
	}

	@Override
	public Integer visit(Sym sym) {
		return 1;
	}

}

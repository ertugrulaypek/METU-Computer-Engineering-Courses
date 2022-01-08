package hw1;

public class PrintMathMLVisitor implements MathVisitor<String> {

	@Override
	public String visit(Op op) {
		if(op.getOperand().equals("/")) {
			return "<mrow><mfrac>" + op.getFirst().accept(this) + op.getSecond().accept(this) + "</mfrac></mrow>";
		}
		else {
			String result = "<mrow><mo>(</mo>" + op.getFirst().accept(this) + "<mo>";
			String operation = op.getOperand();
			if(operation.equals("+")) {
				result = result + "+";
			}
			else if(operation.equals("*")) {
				result = result + "&times;";
			}
			else result = result + "&vdash;";
			
			result = result + "</mo>" +op.getSecond().accept(this) + "<mo>)</mo></mrow>";
			return result;
		}
	}

	@Override
	public String visit(Var var) {
		String result = "";
		if(var.getPreviousMatch() == null) {
			result = result + "<mrow><msub><mi>V</mi><mn>" + var.getId() + "</mn></msub></mrow>";
		}
		else {
			result = result + "<mrow><msub><mi>V</mi><mn>" + var.getId() + "</mn></msub><mo>[</mo>" + var.getPreviousMatch().accept(this) + "<mo>]</mo></mrow>";
		}
		return result;
	}

	@Override
	public String visit(Num num) {
		String result = "<mrow><mn>" + num.getValue() +"</mn></mrow>";
		return result;
	}

	@Override
	public String visit(Sym sym) {
		// TODO Auto-generated method stub
		String result = "<mrow><mi>" + sym.getValue() + "</mi></mrow>";
		return result;
	}
	

}

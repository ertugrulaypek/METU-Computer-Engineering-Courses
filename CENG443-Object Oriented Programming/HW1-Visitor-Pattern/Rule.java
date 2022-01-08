package hw1;

public interface Rule {
	
	public void clear();
	
	abstract MathExpression getPremise();
	
	abstract MathExpression getEntails();
	
	default boolean apply(MathExpression me){
		this.clear();
		boolean matchResult = this.getPremise().match(me);
		if(matchResult) {
			return true;
		}
		else {
			this.clear();
			return false;
		}
	}
	
	default MathExpression entails(MathExpression me) {
		this.apply(me);
		return this.getEntails();
	}
}

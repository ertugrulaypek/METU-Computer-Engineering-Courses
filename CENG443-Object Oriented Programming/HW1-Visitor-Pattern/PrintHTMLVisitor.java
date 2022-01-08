package hw1;
import java.util.ArrayList;
import java.util.Iterator;

public class PrintHTMLVisitor implements TextVisitor<String> {

	@Override
	public String visit(Paragraph paragraph) {
		String result = "<p>" + paragraph.getText() + "</p>";
		return result;
	}

	@Override
	public String visit(EquationText equationText) {
		String result = "<math>" + equationText.getInnerMath().accept(new PrintMathMLVisitor()) + "</math>";
		return result;
	}

	@Override
	public String visit(Document document) {
		String result = "<html><head><title>" + document.getTitle() + "</title></head><body>";  
		ArrayList<DocElement> arr = document.getElements();
		int len = arr.size();
		
		for(int i=0; i<len; i++) {
			DocElement elem = arr.get(i);
			result = result + elem.accept(this);
		}
		result = result + "</body></html>";
		return result;
	}

}

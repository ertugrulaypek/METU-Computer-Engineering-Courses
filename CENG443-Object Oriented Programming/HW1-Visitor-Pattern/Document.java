package hw1;

import java.util.*;

public class Document implements DocElement {

	private String title;
	
	private ArrayList<DocElement> array = new ArrayList<DocElement>();
	
	public Document(String title) { this.title = title; }
	
	public ArrayList<DocElement> getElements() { return this.array; }
	
	public void setElements(ArrayList<DocElement> arr) {
		Collections.copy(this.array, arr);
	}
	
	public void add(DocElement de) {
		this.array.add(de);
	}
	
	public void setTitle(String newTitle) { this.title = newTitle; }
	
	public String getTitle() { return this.title; }
	
	@Override
	public <T> T accept(TextVisitor<T> visitor) {
		return visitor.visit(this);
	}

}

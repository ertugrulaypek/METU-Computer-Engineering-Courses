package hw1;

public interface DocElement {
	public abstract <T> T accept(TextVisitor<T> visitor);
}

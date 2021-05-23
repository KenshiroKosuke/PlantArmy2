package exception;

public class NotAnEmptyCellException extends Exception {
	public NotAnEmptyCellException() {
		super("Cell already has plant");
	}
}

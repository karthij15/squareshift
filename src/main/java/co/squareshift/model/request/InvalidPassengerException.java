package co.squareshift.model.request;

public class InvalidPassengerException extends Exception {

	private static final long serialVersionUID = 2L;

	public InvalidPassengerException(String message) {
		super(message);
	}
}

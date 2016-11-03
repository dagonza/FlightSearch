package org.search.flight.error;

public class InvalidPassengerTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPassengerTypeException() {
		super();
	}

	public InvalidPassengerTypeException(String mensaje) {
		super(mensaje);
	}

	public InvalidPassengerTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPassengerTypeException(Throwable cause) {
		super(cause);
	}
}

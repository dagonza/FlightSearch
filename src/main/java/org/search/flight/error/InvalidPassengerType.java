package org.search.flight.error;

public class InvalidPassengerType extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPassengerType() {
		super();
	}
	
	public InvalidPassengerType(String mensaje) {
		super(mensaje);
	}

	public InvalidPassengerType(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPassengerType(Throwable cause) {
		super(cause);
	}
}

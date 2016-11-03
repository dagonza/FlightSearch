package org.search.flight.error;

public class DateConversionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DateConversionException() {
		super();
	}

	public DateConversionException(String mensaje) {
		super(mensaje);
	}

	public DateConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateConversionException(Throwable cause) {
		super(cause);
	}
}

package br.com.serratec.exception;

public class CamposInvalidosException extends RuntimeException {

	private String message;
	private String error;
	
	public CamposInvalidosException(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}
	
	
}

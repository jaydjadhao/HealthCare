package in.nareshit.raghu.exception;

public class SpecilizationNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SpecilizationNotFoundException() {
		super();
	}
	
	public SpecilizationNotFoundException(String message) {
		super(message);
	}

}

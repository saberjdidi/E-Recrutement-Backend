package tn.recrute.demo.helper;

import java.util.List;

public class InvalidEntityException extends RuntimeException {

	private List<String> errors;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public InvalidEntityException(String message) {
	    super(message);
	  }

	  public InvalidEntityException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public InvalidEntityException(String message, List<String> errors) {
		    super(message);
		    this.errors = errors;
		  }
}

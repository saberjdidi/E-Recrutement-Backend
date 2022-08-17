package tn.recrute.demo.helper;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User with this username not found in DB !");
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

	
}

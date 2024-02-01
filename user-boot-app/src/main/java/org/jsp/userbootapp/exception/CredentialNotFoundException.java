package org.jsp.userbootapp.exception;

public class CredentialNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "invalid Email Or Phone Or Password";
	}

}

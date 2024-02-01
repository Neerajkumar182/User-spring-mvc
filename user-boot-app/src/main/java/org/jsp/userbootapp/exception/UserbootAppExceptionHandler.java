package org.jsp.userbootapp.exception;

import org.jsp.userbootapp.dto.ResponceStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class UserbootAppExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleINFE(IdNotFoundException e){
		ResponceStructure<String> structure = new ResponceStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("User Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
		 
	}
	
	@ExceptionHandler(CredentialNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleINFE(CredentialNotFoundException e){
		ResponceStructure<String> structure = new ResponceStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("Credencial invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponceStructure<String>>(structure,HttpStatus.NOT_FOUND);
		 
	}
	
}

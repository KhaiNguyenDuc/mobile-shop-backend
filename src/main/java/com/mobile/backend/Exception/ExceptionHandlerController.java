package com.mobile.backend.Exception;



import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetails> resourceNotFound(
			ResourceNotFoundException e, WebRequest request){
		ExceptionDetails details = new ExceptionDetails(
				new Date(),
				e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ExceptionDetails> invalidRequest(
			InvalidRequestException e, WebRequest request){
		ExceptionDetails details = new ExceptionDetails(
				new Date(),
				e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceExistException.class)
	public ResponseEntity<ExceptionDetails> resourceExist(
			ResourceExistException e, WebRequest request){
		ExceptionDetails details = new ExceptionDetails(
				new Date(),
				e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionDetails> UserNotFound(
			UserNotFoundException e, WebRequest request){
		ExceptionDetails details = new ExceptionDetails(
				new Date(),
				e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ExceptionDetails> unauthorized(
			UnauthorizedException e, WebRequest request){
		ExceptionDetails details = new ExceptionDetails(
				new Date(),
				e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionDetails> permissionDeny(
			AccessDeniedException e, WebRequest request){
		ExceptionDetails details = new ExceptionDetails(
				new Date(),
				e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.FORBIDDEN);
	}

}

package wharf.studio.realworld.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(InvalidException.class)
	protected ResponseEntity<Object> handleValidationException(InvalidException e) {
		String message = e.getMessage();
		
		ErrorsWrapper result = new ErrorsWrapper();		
		result.addError(message);
		
		return new ResponseEntity<>(result, HttpStatus.UNPROCESSABLE_ENTITY);	
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		ErrorsWrapper result = new ErrorsWrapper();
		
		List<FieldError> fieldErrors = ex.getFieldErrors();
		for(FieldError fieldError: fieldErrors) {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			String error = String.format("[%s] %s", field, message);
			result.addError(error);
		}
		
		return new ResponseEntity<>(result, HttpStatus.UNPROCESSABLE_ENTITY);		
	}
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleNotFoundException() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ForbiddenException.class)
	protected ResponseEntity<Object> handleForbiddenException() {
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Object> handleUnauthorizedException() {
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
}

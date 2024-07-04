package hedwig.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import hedwig.exception.BadMerchantRequestException;
import hedwig.exception.BadSinchCredentialException;
import hedwig.exception.BadSmsRequestException;
import hedwig.exception.BadTwilioCredentialException;

@ControllerAdvice
public class HedwigExceptionHandler {

	@ExceptionHandler(BadMerchantRequestException.class)
	public ResponseEntity<ErrorMessage> handleBadMerchantRequestException(BadMerchantRequestException bmre) {
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), bmre.getMessage(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadSinchCredentialException.class)
	public ResponseEntity<ErrorMessage> handleBadSinchCredentialException(BadSinchCredentialException e) {
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), e.getMessage(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadTwilioCredentialException.class)
	public ResponseEntity<ErrorMessage> handleBadTwilioCredentialException(BadTwilioCredentialException e) {
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), e.getMessage(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadSmsRequestException.class)
	public ResponseEntity<ErrorMessage> handleSmsRequestException(BadSmsRequestException e) {
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), e.getMessage(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}

}

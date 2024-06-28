package hedwig.exception;

/**
 * Exception for bad sms request
 */
public class BadSmsRequestException extends Exception{
	public BadSmsRequestException(String msg) {
		super(msg);
	}

}

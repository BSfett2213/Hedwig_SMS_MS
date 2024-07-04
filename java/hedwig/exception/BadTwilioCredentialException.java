package hedwig.exception;

/**
 * Exception for bad Twilio credentials
 */
public class BadTwilioCredentialException extends Exception {
	public BadTwilioCredentialException(String msg) {
		super(msg);
	}

}

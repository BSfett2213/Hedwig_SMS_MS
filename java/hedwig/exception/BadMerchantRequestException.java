package hedwig.exception;

/**
 * Exception for Bad Merchant operation requests
 */
public class BadMerchantRequestException extends Exception{
	public BadMerchantRequestException(String msg) {
		super(msg);
	}

}

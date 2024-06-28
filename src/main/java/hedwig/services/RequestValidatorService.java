package hedwig.services;

import org.springframework.stereotype.Service;

import hedwig.constants.ErrorConstants;
//import hedwig.dto.MerchantDto;
import hedwig.entity.Merchant;
import hedwig.entity.SinchCredentials;
import hedwig.entity.SmsProvider;
import hedwig.entity.SmsRequest;
import hedwig.entity.TwilioCredentials;
import hedwig.exception.BadMerchantRequestException;
import hedwig.exception.BadSinchCredentialException;
import hedwig.exception.BadSmsRequestException;
import hedwig.exception.BadTwilioCredentialException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestValidatorService {

	public void smsRequestValidator(SmsRequest smsRequest) throws BadSmsRequestException {
		if (smsRequest.getDestinationNum() == null) {
			log.warn(ErrorConstants.DESTINSTION_NUMBER_ERROR);
			throw new BadSmsRequestException(ErrorConstants.DESTINSTION_NUMBER_ERROR);
		} else if (smsRequest.getMerchantID() == 0) {
			log.warn(ErrorConstants.MERCHANT_UUID_ERROR);
			throw new BadSmsRequestException(ErrorConstants.MERCHANT_UUID_ERROR);
		} else if (smsRequest.getMsg() == null) {
			log.warn(ErrorConstants.MESSAGE_ERROR);
			throw new BadSmsRequestException(ErrorConstants.MESSAGE_ERROR);
		}
	}

	public void sinchCredentialValidator(SinchCredentials sinchCredentials) throws BadSinchCredentialException {
		if (sinchCredentials.getAccesskey() == null) {
			log.warn(ErrorConstants.SINCH_ACCESS_KEY_ERROR);
			throw new BadSinchCredentialException(ErrorConstants.SINCH_ACCESS_KEY_ERROR);
		} else if (sinchCredentials.getAccessSecret() == null) {
			log.warn(ErrorConstants.SINCH_ACCESS_SECRET_ERROR);
			throw new BadSinchCredentialException(ErrorConstants.SINCH_ACCESS_SECRET_ERROR);
		} else if (sinchCredentials.getProjectId() == null) {
			log.warn(ErrorConstants.SINCH_PROJECT_ID_ERROR);
			throw new BadSinchCredentialException(ErrorConstants.SINCH_PROJECT_ID_ERROR);
		} else if (sinchCredentials.getSenderPhoneNum() == null) {
			log.warn(ErrorConstants.SENDER_NUMBER_ERROR);
			throw new BadSinchCredentialException(ErrorConstants.SENDER_NUMBER_ERROR);
		}

	}

	public void twilioCredentialValidator(TwilioCredentials twilioCredentials) throws BadTwilioCredentialException {
		if (twilioCredentials.getAccSID() == null) {
			log.warn(ErrorConstants.TWILIO_ACC_SID_ERROR);
			throw new BadTwilioCredentialException(ErrorConstants.TWILIO_ACC_SID_ERROR);
		} else if (twilioCredentials.getAuthToken() == null) {
			log.warn(ErrorConstants.TWILIO_AUTH_TOKEN_ERROR);
			throw new BadTwilioCredentialException(ErrorConstants.TWILIO_AUTH_TOKEN_ERROR);
		} else if (twilioCredentials.getSenderPhoneNum() == null) {
			log.warn(ErrorConstants.SENDER_NUMBER_ERROR);
			throw new BadTwilioCredentialException(ErrorConstants.SENDER_NUMBER_ERROR);
		}
	}

	public void merchantCreationRequestValidator(Merchant merchant)
			throws BadMerchantRequestException, BadTwilioCredentialException, BadSinchCredentialException {
		if (merchant.getPrimarySmsProvider() == null) {
			log.warn(ErrorConstants.PRIMARY_SMS_PROVIDER_ERROR);
			throw new BadMerchantRequestException(ErrorConstants.PRIMARY_SMS_PROVIDER_ERROR);
			
		} else if (merchant.getMerchant_id() == null) {
			log.warn(ErrorConstants.MERCHANT_UUID_ERROR);
			throw new BadMerchantRequestException(ErrorConstants.MERCHANT_UUID_ERROR);
			
		} else if (merchant.getPrimarySmsProvider() == SmsProvider.SINCH) {
			
			if (merchant.getSinchCreds() == null) {
				log.warn(ErrorConstants.SINCH_CREDENTIAL_ERROR);
				throw new BadMerchantRequestException(ErrorConstants.SINCH_CREDENTIAL_ERROR);
				
			} else {
				sinchCredentialValidator(merchant.getSinchCreds());
				
			}
		} else if (merchant.getPrimarySmsProvider() == SmsProvider.TWILIO) {
			
			if (merchant.getTwilioCreds() == null) {
				log.warn(ErrorConstants.TWILIO_CREDENTIAL_ERROR);
				throw new BadMerchantRequestException(ErrorConstants.TWILIO_CREDENTIAL_ERROR);
				
			} else {
				twilioCredentialValidator(merchant.getTwilioCreds());
			}
		}

	}
}

package hedwig.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.sms.models.requests.SendSmsBatchTextRequest;
import com.sinch.sdk.models.Configuration;
import com.sinch.sdk.models.SMSRegion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import hedwig.entity.Merchant;
import hedwig.entity.SmsProvider;
import hedwig.entity.SinchCredentials;
import hedwig.entity.SmsRequest;
import hedwig.entity.TwilioCredentials;
import hedwig.exception.BadSmsRequestException;
import hedwig.repo.MerchantRepo;
import hedwig.repo.SmsRepo;

/**
 * Service class to send messages
 */
@Service
public class MessageService {

	@Autowired
	private MerchantRepo merchantRepo;
	@Autowired
	private SmsRepo smsRepo;
	@Autowired
	private RequestValidatorService requestValidatorService;

	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

	/**
	 * @param twilioCredentials
	 * @param senderNum
	 * @param msg
	 * @return confirmation of msg being sent by Twilio
	 */
	public static String sendMessageByTwilio(TwilioCredentials twilioCredentials, String senderNum, String msg) {
		Twilio.init(twilioCredentials.getAccSID(), twilioCredentials.getAuthToken());

		Message message = Message.creator(new com.twilio.type.PhoneNumber(senderNum),
				new com.twilio.type.PhoneNumber(twilioCredentials.getSenderPhoneNum()), msg).create();
		return "sent";
	}

	/**
	 * @param sinchCredentials
	 * @param phNum
	 * @param msg
	 * @return confirmation of msg being sent by Sinch
	 */
	public static String sendMessageBySinch(SinchCredentials sinchCredentials, String phNum, String msg) {
		SinchClient client = new SinchClient(Configuration.builder().setKeyId(sinchCredentials.getAccesskey())
				.setKeySecret(sinchCredentials.getAccessSecret()).setProjectId(sinchCredentials.getProjectId())
				.setSmsRegion(SMSRegion.US).build());

		client.sms().batches().send(SendSmsBatchTextRequest.builder().setTo(Collections.singletonList(phNum))
				.setBody(msg).setFrom(sinchCredentials.getSenderPhoneNum()).build());

		return "sent";
	}

	/**
	 * controls which method to use to send message based on merchant.primarySmsProvider
	 * @param smsRequest
	 * @returns 
	 * @throws BadSmsRequestException
	 */
	public String sendSms(SmsRequest smsRequest) throws BadSmsRequestException {
		requestValidatorService.smsRequestValidator(smsRequest);
		Merchant merchant = merchantRepo.getReferenceById(smsRequest.getMerchantID());
		smsRepo.save(smsRequest);
		if (merchant.getPrimarySmsProvider() == SmsProvider.SINCH)
			sendMessageBySinch(merchant.getSinchCreds(), smsRequest.getDestinationNum(), smsRequest.getMsg());
		else
			sendMessageByTwilio(merchant.getTwilioCreds(), smsRequest.getDestinationNum(), smsRequest.getMsg());
		return "sent";

	}

	public Merchant getMerchant(int merchantID) {
		return merchantRepo.getReferenceById(merchantID);
	}

}

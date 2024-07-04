package hedwig.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hedwig.dto.MerchantDto;
import hedwig.entity.SinchCredentials;
import hedwig.entity.SmsProvider;
import hedwig.entity.TwilioCredentials;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CensorshipService {

	public MerchantDto twilioCensorhip(MerchantDto merchantDto) {
		String sid = censorStringExceptLastFour(merchantDto.getDtoTwilioCreds().getAccSID());
		String auth = censorStringExceptLastFour(merchantDto.getDtoTwilioCreds().getAuthToken());
		String num = censorStringExceptLastFour(merchantDto.getDtoTwilioCreds().getSenderPhoneNum());

		log.info("after censoring :");
		log.info("SID : "+sid+" authToken : "+auth+" number : "+num);
		merchantDto.getDtoTwilioCreds().setAccSID(sid);
		merchantDto.getDtoTwilioCreds().setAuthToken(auth);
		merchantDto.getDtoTwilioCreds().setSenderPhoneNum(num);
		log.info("returning DTO : "+merchantDto);
		return merchantDto;
	}

	public MerchantDto sinchCensorship(MerchantDto merchantDto) {
		String key = censorStringExceptLastFour(merchantDto.getDtoSinchCreds().getAccesskey());
		String secret = censorStringExceptLastFour(merchantDto.getDtoSinchCreds().getAccessSecret());
		String proj = censorStringExceptLastFour(merchantDto.getDtoSinchCreds().getProjectId());
		String num = censorStringExceptLastFour(merchantDto.getDtoSinchCreds().getSenderPhoneNum());
		log.info("after censoring :");
		log.info("key : "+key+" secret : "+secret+" projectID : "+proj+" number : "+num);
		merchantDto.getDtoSinchCreds().setAccesskey(key);
		merchantDto.getDtoSinchCreds().setAccessSecret(secret);
		merchantDto.getDtoSinchCreds().setProjectId(proj);
		merchantDto.getDtoSinchCreds().setSenderPhoneNum(num);

		log.info("returning DTO after censoring sinch credentials : "+merchantDto);
		return merchantDto;

	}

	public List<MerchantDto> returnCensorList(List<MerchantDto> merchantDtoList) {
		List<MerchantDto> censoredList = new ArrayList<>();
		for (MerchantDto merchantDto : merchantDtoList) {
			log.info("Current merchant DTO before censor is : "+merchantDto);
			merchantDto = censordMerchantDto(merchantDto);
			log.info("Current DTO after censor : "+merchantDto);
			censoredList.add(merchantDto);
		}
		return censoredList;
	}

	public MerchantDto censordMerchantDto(MerchantDto merchantDto) {
		log.info("Censoring sinch crederntials of DTO : "+merchantDto);
		MerchantDto partialCensor = sinchCensorship(merchantDto);
		log.info("Censoring twilio credentials of DTO : "+partialCensor);
		return twilioCensorhip(partialCensor);
	}

	public static String censorStringExceptLastFour(String input) {
		int length = input.length();
		String censored = "";
		log.info("censor String : "+censored);
		for (int i = 0; i < length - 4; i++) {
			censored = censored.concat("*");
		}
		censored = censored.concat(input.substring(length - 4));
		return censored;
	}
}

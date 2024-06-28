package hedwig.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

//import hedwig.dto.MerchantDto;
import hedwig.entity.SmsProvider;

@Service
public class CensorshipService {

//	public MerchantDto twilioCensorhip(MerchantDto merchantDto) {
//		String sid = merchantDto.getTwilioCreds().getAccSID();
//		String auth = merchantDto.getTwilioCreds().getAuthToken();
//		String num = merchantDto.getTwilioCreds().getSenderPhoneNum();
//		merchantDto.getTwilioCreds().setAccSID(censorStringExceptLastFour(sid));
//		merchantDto.getTwilioCreds().setAuthToken(censorStringExceptLastFour(auth));
//		merchantDto.getTwilioCreds().setSenderPhoneNum(num);
//		return merchantDto;
//	}
//
//	public MerchantDto sinchCensorship(MerchantDto merchantDto) {
//		String key = merchantDto.getSinchCred().getAccesskey();
//		String secret = merchantDto.getSinchCred().getAccessSecret();
//		String proj = merchantDto.getSinchCred().getProjectId();
//		String num = merchantDto.getSinchCred().getSenderPhoneNum();
//		merchantDto.getSinchCred().setAccesskey(censorStringExceptLastFour(key));
//		merchantDto.getSinchCred().setAccessSecret(secret);
//		merchantDto.getSinchCred().setProjectId(censorStringExceptLastFour(proj));
//		merchantDto.getSinchCred().setSenderPhoneNum(censorStringExceptLastFour(num));
//		return merchantDto;
//
//	}
//
//	public List<MerchantDto> returnCensorList(List<MerchantDto> merchantDtoList) {
//		List<MerchantDto> censoredList = new ArrayList<>();
//		for (MerchantDto merchantDto : merchantDtoList) {
//			if (merchantDto.getPrimarySmsProvider() == SmsProvider.SINCH)
//				censoredList.add(sinchCensorship(merchantDto));
//			else
//				censoredList.add(twilioCensorhip(merchantDto));
//		}
//		return censoredList;
//	}
//
//	public MerchantDto censordMerchantDto(MerchantDto merchantDto) {
//		if (merchantDto.getPrimarySmsProvider() == SmsProvider.SINCH)
//			return sinchCensorship(merchantDto);
//		else
//			return twilioCensorhip(merchantDto);
//	}
//
//	public String censorStringExceptLastFour(String input) {
//		int length = input.length();
//		String censored = "";
//		for (int i = 0; i < length - 4; i++) {
//			censored.concat("X");
//		}
//		censored.concat(input.substring(length - 4));
//		return censored.toString();
//	}
}

package hedwig.dto;

import hedwig.entity.Merchant;
import hedwig.entity.SinchCredentials;
import hedwig.entity.SmsProvider;
import hedwig.entity.TwilioCredentials;
import lombok.Data;

@Data
public class MerchantDto {
	
	public int dtoUuid;
	public String dtoMerchant_id;
	public SinchCredentials dtoSinchCreds;
	public TwilioCredentials dtoTwilioCreds;	
	public SmsProvider dtoPrimarySmsProvider;
	
	public MerchantDto(Merchant merchant) {
		dtoUuid = merchant.getUuid();
		dtoMerchant_id = merchant.getMerchant_id();
		dtoPrimarySmsProvider = merchant.getPrimarySmsProvider();
		dtoSinchCreds = merchant.getSinchCreds();
		dtoTwilioCreds = merchant.getTwilioCreds();
	}
	
	
	public MerchantDto(int dtoUuid, String dtoMerchant_id, SinchCredentials dtoSinchCreds,
			TwilioCredentials dtoTwilioCreds, SmsProvider dtoPrimarySmsProvider) {
		this.dtoUuid = dtoUuid;
		this.dtoMerchant_id = dtoMerchant_id;
		this.dtoSinchCreds = dtoSinchCreds;
		this.dtoTwilioCreds = dtoTwilioCreds;
		this.dtoPrimarySmsProvider = dtoPrimarySmsProvider;
	}
	
	

}

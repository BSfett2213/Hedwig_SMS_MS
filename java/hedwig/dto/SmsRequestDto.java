package hedwig.dto;

import hedwig.entity.SmsRequest;
import lombok.Data;


@Data
public class SmsRequestDto {
	private String msg;
	private int merchantID;
	private String destinationNum;
	public SmsRequestDto(String msg, int merchantID, String destinationNum) {
		this.msg = msg;
		this.merchantID = merchantID;
		this.destinationNum = destinationNum;
	}
	public SmsRequestDto(SmsRequest smsRequest) {
		this.msg = smsRequest.getMsg();
		this.merchantID = smsRequest.getMerchantID();
		this.destinationNum = smsRequest.getDestinationNum();
	}
	

}

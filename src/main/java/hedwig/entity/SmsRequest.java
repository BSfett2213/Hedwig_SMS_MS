package hedwig.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class for sms_request table
 * primary key : smsID(int)
 * records all messages sent using Hedwig MS
 */
@Entity
@Data
@Table(name = "sms_request")
public class SmsRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_id")
	private int smsID;
	@Column(name = "merchant_uuid")
	private int merchantID;
	@Column(name = "reciever_phone_number")
	private String destinationNum;
	@Column(name = "message_content")
	private String msg;

}

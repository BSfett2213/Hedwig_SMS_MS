package hedwig.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class for table twilio_credentials, holds login info for Twilio
 * primary key : uuid(int)
 * OneToOne join to merchant class
 */
@Entity
@Data
@Table(name = "twilio_credential")
public class TwilioCredentials implements Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "twilio_uuid")
	private int uuid;
	@Column(name = "twilio_account_sid")
	private String accSID;
	@Column(name = "twilio_authentication_token")
	private String authToken;
	@Column(name = "twilio_origin_phone_number")
	private String senderPhoneNum;
}

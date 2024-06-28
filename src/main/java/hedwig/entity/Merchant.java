package hedwig.entity;


import java.util.Objects;


import hedwig.dto.MerchantDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class for merchant table
 * primary key : uuid (int)
 * OneToOne join on SinchCredentials and TwilioCredentials
 * 
 * overriden equals and hashCode method for detecting objects with same uuid
 */
@Entity
@Data
@Table(name = "merchant")
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uuid")
	private int uuid;
	
	@Column( name = "merchant_id")
	private String merchant_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sinch_credentials_uuid")
	private SinchCredentials sinchCreds;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "twilio_credential_uuid")
	private TwilioCredentials twilioCreds;
	
	@Column(name = "primary_sms_provider")
	private SmsProvider primarySmsProvider;
	
	public Merchant(MerchantDto merchantDto) {
		uuid = merchantDto.getDtoUuid();
		merchant_id = merchantDto.getDtoMerchant_id();
		sinchCreds = merchantDto.getDtoSinchCreds();
		twilioCreds = merchantDto.getDtoTwilioCreds();
		primarySmsProvider = merchantDto.getDtoPrimarySmsProvider();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Merchant other = (Merchant) obj;
		return Objects.equals(uuid, other.uuid);
	}
	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}
//	public Merchant(MerchantDto m) {
//		this.merchant_id = m.getMerchant_id();
//		this.primarySmsProvider = m.getPrimarySmsProvider();
//		this.sinchCreds = m.getSinchCred();
//		this.twilioCreds= m.getTwilioCreds();
//		this.uuid = m.getUuid();
//		
//	}
	public Merchant() {
	}
	
		
	

}

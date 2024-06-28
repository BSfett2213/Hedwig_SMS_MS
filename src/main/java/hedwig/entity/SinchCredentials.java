package hedwig.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
/**
 * Entity class for table sinch_credentials, holds login info for Sinch
 * primary key : uuid(int)
 * OneToOne join to merchant class
 */
@Entity
@Data
@Table(name = "sinch_credential")
public class SinchCredentials implements Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sinch_uuid")
	private int uuid;
	@Column(name = "sinch_project_id")
	private String projectId;
	@Column(name = "sinch_access_key")
	private String accesskey;
	@Column(name = "sinch_access_secret")
	private String accessSecret;
	@Column(name = "sinch_origin_phone_number")
	private String senderPhoneNum;
}

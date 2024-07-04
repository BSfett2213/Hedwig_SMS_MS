package hedwig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hedwig.dto.MerchantDto;
import hedwig.entity.Merchant;
import hedwig.exception.BadMerchantRequestException;
import hedwig.exception.BadSinchCredentialException;
import hedwig.exception.BadTwilioCredentialException;
import hedwig.services.MerchantServices;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for CRUD operations on mercchant table
 */
@Slf4j
@RestController
@RequestMapping("/hedwig/")
public class MerchantController {
	@Autowired
	private MerchantServices merchantServices;

	/**
	 * @return List<Merchant>
	 */
	@GetMapping("merchant")
	public List<MerchantDto> getMerchantList() {
		log.info("get request sent");
		return  merchantServices.getAllMerchants();
	}

	/**
	 * @param merchant_uuid
	 * @return Merchant
	 */
	@GetMapping("merchant/ByID")
	public MerchantDto getMerchant(@RequestParam int id) {
		return merchantServices.retrieveMerchantDetails(id);
	}

	/**
	 * @param merchant
	 * @return inserted merchant
	 * @throws BadTwilioCredentialException
	 * @throws BadSinchCredentialException
	 * @throws BadMerchantRequestException
	 */
	@PostMapping("merchant")
	public MerchantDto addMerchant(@RequestBody Merchant merchantRequest)
			throws BadMerchantRequestException, BadSinchCredentialException, BadTwilioCredentialException {
		return merchantServices.createMerchant(merchantRequest);
//		return (merchant);
	}

	/**
	 * @param merchant_uuid
	 * @return confirmation of merchant deletion
	 */
	@DeleteMapping("merchant")
	public String deleteMerchant(@RequestParam int id) {
		return merchantServices.deleteMerchant(id); 
	}
}

package hedwig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hedwig.entity.Merchant;
import hedwig.exception.BadMerchantRequestException;
import hedwig.exception.BadSinchCredentialException;
import hedwig.exception.BadTwilioCredentialException;
import hedwig.services.MerchantServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for CRUD operations on mercchant table
 */
@RestController
@RequestMapping("/hedwig/")
public class MerchantController {
	@Autowired
	private MerchantServices merchantServices;

	/**
	 * @return List<Merchant>
	 */
	@GetMapping("merchant")
	public List<Merchant> getMerchantList() {
		return  merchantServices.getAllMerchants();
	}

	/**
	 * @param merchant_uuid
	 * @return Merchant
	 */
	@GetMapping("merchant/ByID")
	public Merchant getMerchant(@RequestParam int id) {
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
	public Merchant addMerchant(@RequestBody Merchant merchantRequest)
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

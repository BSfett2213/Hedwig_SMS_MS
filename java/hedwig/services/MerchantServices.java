package hedwig.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hedwig.dto.MerchantDto;
//import hedwig.dto.MerchantDto;
import hedwig.entity.Merchant;
import hedwig.exception.BadMerchantRequestException;
import hedwig.exception.BadSinchCredentialException;
import hedwig.exception.BadTwilioCredentialException;
import hedwig.repo.MerchantRepo;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for CRUD operations on Merchants
 */
@Slf4j
@Service
public class MerchantServices {
	@Autowired
	private MerchantRepo merchantRepo;
	@Autowired
	private RequestValidatorService requestValidatorService;
	@Autowired
	private CensorshipService censorshipService;

	/**
	 * @param Object of type Merchant
	 * @return object of type Merchant saved in Merchant repo
	 * @throws BadTwilioCredentialException
	 * @throws BadSinchCredentialException
	 * @throws BadMerchantRequestException
	 */
	public MerchantDto createMerchant(Merchant m) throws BadMerchantRequestException, BadSinchCredentialException, BadTwilioCredentialException {
		requestValidatorService.merchantCreationRequestValidator(m);
		Merchant merchant = merchantRepo.save(m);
		MerchantDto merchantDto = new MerchantDto(merchant);
		return censorshipService.censordMerchantDto(merchantDto);
	}

	/**
	 * @param merchant_uuid
	 * @return Object of type Merchant having provided merchant_uuid
	 */
	public MerchantDto retrieveMerchantDetails(int merchantID) {
		Merchant merchant = merchantRepo.getReferenceById(merchantID);
		return censorshipService.censordMerchantDto(new MerchantDto(merchant));
	}

	/**
	 * @param Object of type Merchant
	 * @return Object of type MErchant after updated attributes
	 * @throws BadTwilioCredentialException
	 * @throws BadSinchCredentialException
	 * @throws BadMerchantRequestException
	 */
//	public Merchant updateMerchant(Merchant m) throws BadMerchantRequestException, BadTwilioCredentialException, BadSinchCredentialException {
//		requestValidatorService.merchantCreationRequestValidator(m);
//		merchantRepo.deleteById(m.getUuid());
//		Merchant updatedMerch = merchantRepo.save(m);
//		return updatedMerch;
//	}

	/**
	 * @return List<Merchants> of all merchant
	 */
	public List<MerchantDto> getAllMerchants() {
		log.info("Entering getAllMerchants method");
		List<MerchantDto> dtoList= new ArrayList<>();
		for(Merchant m : merchantRepo.findAll()) {
			log.info("converting merchant to DTO : "+m);
			MerchantDto merchantDto = new MerchantDto(m);
			log.info("Adding DTO to list"+merchantDto);
			dtoList.add(merchantDto);
		}
		log.info("The list is : "+dtoList);
		return censorshipService.returnCensorList(dtoList);
	}
	
	/**
	 * @param merchant_uuid
	 * @return Confirmation of merchant deletion
	 */
	public String deleteMerchant(int id) {
		merchantRepo.deleteById(id);
		return "Deleted";
	}

}

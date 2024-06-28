package hedwig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hedwig.entity.SmsRequest;
import hedwig.exception.BadSmsRequestException;
import hedwig.services.MessageService;

/**
 * Controller class for message operations 
 */
@RestController
@RequestMapping("/hedwig/")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	
	/**
	 * @param SmsRequest object
	 * @return Confirmation of message being sent
	 * @throws BadSmsRequestException 
	 */
	@GetMapping("send")
	public String sendMessage(@RequestBody SmsRequest msgDetails) throws BadSmsRequestException {
		return messageService.sendSms(msgDetails);
		
	}

}

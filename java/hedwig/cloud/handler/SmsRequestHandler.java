package hedwig.cloud.handler;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;
import hedwig.config.ErrorMessage;
import hedwig.dto.SmsRequestDto;
import hedwig.entity.SmsRequest;
import hedwig.services.MessageService;
import hedwig.utils.ObjectMapperFunctions;
import jakarta.transaction.Transactional;

@Component
public class SmsRequestHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Autowired
	private MessageService messageService;

	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent input) {
		try {
			String inputBody = input.getBody();
			ObjectMapper mapper = new ObjectMapper();
			SmsRequest smsRequest = mapper.readValue(inputBody, SmsRequest.class);
			SmsRequestDto smsRequestDto = new SmsRequestDto(smsRequest);
			messageService.sendSms(smsRequest);
			return new APIGatewayProxyResponseEvent()
					.withBody(mapper.writeValueAsString(smsRequestDto))
					.withStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			ErrorMessage em = new ErrorMessage(400, new Date(), e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
			return new APIGatewayProxyResponseEvent().withBody(ObjectMapperFunctions.jsonConverter(em)).withStatusCode(400);
		}

	}
}

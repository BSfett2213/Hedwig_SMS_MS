package hedwig.cloud.handler;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import hedwig.config.ErrorMessage;
import hedwig.dto.MerchantDto;
import hedwig.entity.Merchant;
import hedwig.services.MerchantServices;
import hedwig.utils.ObjectMapperFunctions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MerchantHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Autowired
	private MerchantServices merchantServices;

	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent input) {
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		try {
			String inputBody = input.getBody();
			switch (input.getHttpMethod()) {
			case "GET":
				responseEvent.setBody(merchantServices.getAllMerchants().toString());
				break;
			case "POST":
				ObjectMapper mapper = new ObjectMapper();
				log.info("Prior to object mapping");
				Merchant merchant = mapper.readValue(inputBody, Merchant.class);
				log.info("Object mapped is : " + merchant);
				responseEvent.setBody(merchantServices.createMerchant(merchant).toString());
				break;
			case "DELETE":
				Map<String, String> inputMap = input.getQueryStringParameters();
				int id = Integer.parseInt(inputMap.get("id"));
				MerchantDto deletedMerchant = merchantServices.retrieveMerchantDetails(id);
				merchantServices.deleteMerchant(id);
				responseEvent.setBody("Merchant Deleted is : "+deletedMerchant);
			default:
				throw new IllegalArgumentException("Unexpected value: " + input.getHttpMethod());
			}
			return responseEvent.withStatusCode(200);

		} catch (Exception e) {
			e.printStackTrace(System.out);
			ErrorMessage em = new ErrorMessage(400, new Date(), e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
			return responseEvent.withBody(ObjectMapperFunctions.jsonConverter(em)).withStatusCode(400);
		}
	}

}

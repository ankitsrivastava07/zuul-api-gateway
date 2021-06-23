package gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestFallback {

	@RequestMapping("/users-fallback-uri")
	public ResponseConstant usersCircuitBreaker(Throwable exception) {
		ResponseConstant responseStatus = new ResponseConstant();

		exception.printStackTrace();

		return responseStatus;
	}

	@RequestMapping("/jwt-session-fallback-uri")
	public ResponseConstant jwtCircuitBreaker(Throwable exception) {
		ResponseConstant responseStatus = new ResponseConstant();
		return responseStatus;
	}

}

package gateway.fallback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestFallback {

	@RequestMapping("/users-fallback-uri")
	public ResponseEntity<?> usersCircuitBreaker(Throwable exception) {
		ResponseConstant responseStatus = new ResponseConstant();

		exception.printStackTrace();

		return new ResponseEntity<>(responseStatus, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@RequestMapping("/jwt-session-fallback-uri")
	public ResponseEntity<?> jwtCircuitBreaker(Throwable exception) {
		ResponseConstant responseStatus = new ResponseConstant();
		return new ResponseEntity<>(responseStatus, HttpStatus.SERVICE_UNAVAILABLE);
	}

}

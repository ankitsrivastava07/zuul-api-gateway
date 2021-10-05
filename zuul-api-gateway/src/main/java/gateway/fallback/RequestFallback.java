package gateway.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestFallback {
	private @Autowired ResponseConstant responseStatus;
	@RequestMapping("/users-fallback")
	public ResponseEntity<?> usersCircuitBreaker(Throwable exception) {
		exception.printStackTrace();
		return new ResponseEntity<>(responseStatus, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@RequestMapping("/jwt-session-fallback")
	public ResponseEntity<?> jwtCircuitBreaker(Throwable exception) {
		return new ResponseEntity<>(responseStatus, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@RequestMapping("/order-microservice-fallback")
	public ResponseEntity<?> orderMicroserviceFallback(Throwable exception) {
		return new ResponseEntity<>(responseStatus, HttpStatus.SERVICE_UNAVAILABLE);
	}

}

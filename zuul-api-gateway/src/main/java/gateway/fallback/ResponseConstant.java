package gateway.fallback;

import org.springframework.http.HttpStatus;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseConstant {

	private Integer httpStatus = HttpStatus.SERVICE_UNAVAILABLE.value();
	private Boolean status = Boolean.FALSE;
	private String message = "Sorry server is currently down. Please try again later.";
	private String path;
}
package zuulapigateway.fallback;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

	private Integer status;
	private String error;
	private String message;
	private String path;

	public ApiError(Integer status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
}
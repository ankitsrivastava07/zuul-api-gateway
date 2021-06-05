package zuulapigateway.fallback;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErrorFilter extends ZuulFilter {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable thr = ctx.getThrowable();
		Object e = ctx.get("error.exception");

		return true;
	}

	@Override
	public Object run() {
		ApiError apiError = null;
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			Object e = ctx.get("error.exception");

			Throwable throwable = ctx.getThrowable();

			apiError = new ApiError(ctx.getResponseStatusCode(), throwable.getMessage(), throwable.getMessage(),
					ctx.getRequest().getRequestURI());

			if (e != null && e instanceof ZuulException) {
				ZuulException zuulException = (ZuulException) e;
				// LOG.error("Zuul failure detected: " + zuulException.getMessage(),
				// zuulException);

				// Remove error code to prevent further error handling in follow up filters
				ctx.remove("error.status_code");

				// Populate context with new response values
				ctx.setResponseBody("Overriding Zuul Exception Body");
				ctx.getResponse().setContentType("application/json");
				ctx.setResponseStatusCode(500); // Can set any error code as excepted
			}
		} catch (Exception ex) {
			// LOG.error("Exception filtering in custom error filter", ex);
			ReflectionUtils.rethrowRuntimeException(ex);
		}
		return apiError;
	}
}

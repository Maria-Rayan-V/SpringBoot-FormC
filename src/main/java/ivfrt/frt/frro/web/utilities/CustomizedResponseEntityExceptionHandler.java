package ivfrt.frt.frro.web.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		List<String> errors1 = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
			errors1.add(violation.getMessage());
		}

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("errors", errors1);

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("errors", error);
		body.put("status", status.value());
		
		return new ResponseEntity<>(body, headers, status);

	}

	


	
	
	 @ExceptionHandler({ MethodArgumentTypeMismatchException.class }) 
	 public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	 			MethodArgumentTypeMismatchException ex, WebRequest request) { 
		 String error =ex.getName() + " should be of type " + ex.getRequiredType().getName();
		 
		 Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", new Date());
			body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			body.put("errors", error);
			return new ResponseEntity<>(body, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	 
	 
	 @Override
	 protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	   HttpRequestMethodNotSupportedException ex, 
	   HttpHeaders headers, 
	   HttpStatus status, 
	   WebRequest request) {
	     StringBuilder builder = new StringBuilder();
	     builder.append(ex.getMethod());
	     builder.append(" method is not supported for this request. Supported methods are ");
	     ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	     Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", new Date());
			body.put("status", status.value());
			body.put("errors", builder);
			return new ResponseEntity<>(body, headers, status);
	     
	   
	 }
	 
	 @ExceptionHandler(MissingRequestHeaderException.class)
	    public final ResponseEntity<Object> handleInvalidTraceIdException
	                        (MissingRequestHeaderException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        
	        StringBuilder builder = new StringBuilder();
		     builder.append(ex.getMessage());
		     builder.append(" Some unexpected exception.");
		     Map<String, Object> body = new LinkedHashMap<>();
				body.put("timestamp", new Date());
				body.put("status", HttpStatus.UNAUTHORIZED);
				body.put("errors", builder);
				return new ResponseEntity<>(body, new HttpHeaders(),  HttpStatus.UNAUTHORIZED);
	        
	    }
	
	 @ExceptionHandler({ IllegalArgumentException.class }) 
	 public ResponseEntity<Object> handleIllegalArgumentException(
			 IllegalArgumentException ex, WebRequest request) { 
		 String error =ex.getMessage() ;
		 
		 Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", new Date());
			body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			body.put("errors", error);
			return new ResponseEntity<>(body, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	   
		@ExceptionHandler({ Exception.class })
		public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
			StringBuilder builder = new StringBuilder();
		     builder.append(ex.getMessage());
		     builder.append(" Some unexpected exception.");
		     Map<String, Object> body = new LinkedHashMap<>();
				body.put("timestamp", new Date());
				body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
				body.put("errors", builder);
				return new ResponseEntity<>(body, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 @ExceptionHandler({IllegalStateException.class })
			    protected ResponseEntity<Object> handleConflict(
			      RuntimeException ex, WebRequest request) {
			        String bodyOfResponse = "This should be application specific";
			        return handleExceptionInternal(ex, bodyOfResponse, 
			          new HttpHeaders(), HttpStatus.CONFLICT, request);
			    }
	   
}

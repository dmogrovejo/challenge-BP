package com.challengebp.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author David Mogrovejo
 */
@ControllerAdvice
public class BPExceptionHandler extends ResponseEntityExceptionHandler {

	// handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

		details.add(builder.toString());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Invalid JSON", details);

		return ResponseEntityBuilder.build(err);

	}

	/***
	 * Metodo que captura el error cuando el JSON está mal formado
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Malformed JSON request", details);

		return ResponseEntityBuilder.build(err);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getObjectName() + " : " + error.getDefaultMessage()).collect(Collectors.toList());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Validation Errors", details);

		return ResponseEntityBuilder.build(err);
	}


	/**
	 * Metodo que captura el Error cuando los parametros son incorrectos o tiene valores invalidos
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getParameterName() + " parameter is missing");

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Missing Parameters", details);

		return ResponseEntityBuilder.build(err);
	}

	// handleMethodArgumentTypeMismatch : triggers when a parameter's type does not
	// match
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Mismatch Type", details);

		return ResponseEntityBuilder.build(error);
	}

	/**
	 * Metodo que lanza el mensaje en caso de que se viole la restricción de Integridad
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Constraint Violation", details);

		return ResponseEntityBuilder.build(err);
	}
	
	/**
	 * Capturas los errores que se producen al momento de Administrar las cuentas 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<Object> handleAccountException(AccountException ex) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.PRECONDITION_FAILED, "Pre condition failed: ", details);

		return ResponseEntityBuilder.build(err);
	}
	
	/**
	 * Capturas los errores que se producen al momento de realizar moviemintos en las cuentas 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MovementException.class)
	public ResponseEntity<Object> handleMovementException(MovementException ex) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.PRECONDITION_FAILED, "Pre condition failed: ", details);

		return ResponseEntityBuilder.build(err);
	}
	

	/**
	 * Metodo que lanza el mensaje en caso de que no la consulta no devuelve ningún 
	 * Registro
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND, "Resource Not Found", details);

		return ResponseEntityBuilder.build(err);
	}

	/**
	 * En caso de que se invoque una URL que no exista dentro de los  END POINTS, el sistema alerta de aquello
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Method Not Found", details);

		return ResponseEntityBuilder.build(err);

	}

	
	/**
	 * En caso de que se presente un error inesperado que no se haya  capturado el sistema 
	 * informa 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getLocalizedMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Error occurred", details);

		return ResponseEntityBuilder.build(err);

	}

}

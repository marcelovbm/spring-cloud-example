package com.example.restfull.websrevices.restfullwebservices.exception;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public final ExceptionResponse handleAllInternalServerErrorException(Exception ex, WebRequest request) {

		return new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ UserNotFoundException.class, PostsNotFoundException.class })
	public final ExceptionResponse handleAllNotFoundException(Exception ex, WebRequest request) {

		return new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Optional<FieldError> fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst();
		String defaultMessage = "";

		if (fieldError.isPresent())
			defaultMessage = fieldError.get().getField() + ": " + fieldError.get().getDefaultMessage();

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed!", defaultMessage);

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}

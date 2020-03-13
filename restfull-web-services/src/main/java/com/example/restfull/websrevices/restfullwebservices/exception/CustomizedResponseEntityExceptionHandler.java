package com.example.restfull.websrevices.restfullwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
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
	public final ExceptionResonse handleAllInternalServerErrorException(Exception ex, WebRequest request) {

		return new ExceptionResonse(new Date(), ex.getMessage(), request.getDescription(false));
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ UserNotFoundException.class, PostsNotFoundException.class })
	public final ExceptionResonse handleAllNotFoundException(Exception ex, WebRequest request) {

		return new ExceptionResonse(new Date(), ex.getMessage(), request.getDescription(false));
	}
}

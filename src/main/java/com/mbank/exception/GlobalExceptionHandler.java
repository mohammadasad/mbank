package com.mbank.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mbank.entity.ApiResponse;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger  = LogManager.getLogger(GlobalExceptionHandler.class);
	ApiResponse apiError=null;
	
	   @ExceptionHandler(Exception.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        ResponseEntity ressonseEntity=null;
	        String errorMessage=null;
	        details.add(ex.getLocalizedMessage());
	        errorMessage = "Server Error";
	        apiError = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),errorMessage,null);
	        ressonseEntity=new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	        logger.error(details,ex);
	        return ressonseEntity;
	    }

	   @ExceptionHandler(RecordNotFoundException.class)
	    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
	        apiError = new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage(),null);
	        logger.error(apiError,ex);
	        return new ResponseEntity(apiError,HttpStatus.NOT_FOUND);
	    }
}

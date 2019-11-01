package com.ts.tutorials.spring.demoserver.web.util;

import com.ts.tutorials.spring.demoserver.web.beans.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private static final HashSet<Class<? extends Throwable>> BAD_REQUEST_EXCEPTIONS = new HashSet<>();
    private static final HashSet<Class<? extends Throwable>> NOT_FOUND_EXCEPTIONS = new HashSet<>();

    static {
        BAD_REQUEST_EXCEPTIONS.add(DataIntegrityViolationException.class);
        BAD_REQUEST_EXCEPTIONS.add(ConstraintViolationException.class);

        NOT_FOUND_EXCEPTIONS.add(HttpRequestMethodNotSupportedException.class);
        NOT_FOUND_EXCEPTIONS.add(IllegalArgumentException.class);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ApiError> handleException(Throwable e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (isBadRequest(e)) {
            status = HttpStatus.BAD_REQUEST;
        } else if (isNotFound(e)) {
            status = HttpStatus.NOT_FOUND;
        } else {
            logger.error("Unhandled exception occurred.", e);
        }
        return new ResponseEntity<>(new ApiError(status.getReasonPhrase()), status);
    }

    private boolean isBadRequest(Throwable e) {
        boolean isBadRequest = BAD_REQUEST_EXCEPTIONS.contains(e.getClass());
        if (!isBadRequest && e instanceof TransactionSystemException) {
            Throwable rootCause = ((TransactionSystemException)e).getRootCause();
            return BAD_REQUEST_EXCEPTIONS.contains(rootCause.getClass());
        }
        return isBadRequest;
    }

    private boolean isNotFound(Throwable e) {
        return NOT_FOUND_EXCEPTIONS.contains(e.getClass());
    }

}

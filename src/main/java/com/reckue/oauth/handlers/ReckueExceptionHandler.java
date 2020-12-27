package com.reckue.oauth.handlers;

import com.reckue.libs.exception.ReckueException;
import com.reckue.libs.responses.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.reckue.oauth.handlers.distributor.CodeErrorDistributor.codeErrors;
import static com.reckue.oauth.handlers.distributor.HttpStatusErrorDistributor.httpStatuses;

@RestControllerAdvice
public class ReckueExceptionHandler {

    @ExceptionHandler(ReckueException.class)
    public ResponseEntity<?> handleReckueException(ReckueException e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .title(e.getClass().getSimpleName())
                .code(codeErrors.get(e.getClass()))
                .message(e.getMessage())
                .trace(ExceptionUtils.getStackTrace(e))
                .build(), httpStatuses.get(e.getClass()));
    }
}
package com.reckue.oauth.handlers.distributor;

import com.reckue.libs.exception.*;
import com.reckue.oauth.model.exception.credentials.PasswordCredentialsAlreadyExistsException;
import com.reckue.oauth.model.exception.credentials.PasswordCredentialsNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusErrorDistributor {
    public static Map<Class<? extends ReckueException>, HttpStatus> httpStatuses = new HashMap<>();

    static {
        httpStatuses.put(ModelAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(PasswordCredentialsAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(ModelNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(PasswordCredentialsNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(ReckueIllegalArgumentException.class, HttpStatus.BAD_REQUEST);
        httpStatuses.put(ReckueUnauthorizedException.class, HttpStatus.UNAUTHORIZED);
        httpStatuses.put(ReckueAccessDeniedException.class, HttpStatus.FORBIDDEN);
    }
}
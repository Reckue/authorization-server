package com.reckue.oauth.handlers;

import com.reckue.libs.exception.*;
import com.reckue.oauth.model.exceptions.PasswordCredentialsAlreadyExistsException;
import com.reckue.oauth.model.exceptions.PasswordCredentialsNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CodeErrorDistributor {

    public static Map<Class<? extends ReckueException>, String> codeErrors = new HashMap<>();

    static {
        codeErrors.put(ModelAlreadyExistsException.class, "ROE-000");
        codeErrors.put(PasswordCredentialsAlreadyExistsException.class, "ROE-001");
        codeErrors.put(ModelNotFoundException.class, "ROE-100");
        codeErrors.put(PasswordCredentialsNotFoundException.class, "ROE-101");
        codeErrors.put(ReckueIllegalArgumentException.class, "ROE-200");
        codeErrors.put(ReckueUnauthorizedException.class, "ROE-300");
        codeErrors.put(ReckueAccessDeniedException.class, "ROE-400");
    }
}

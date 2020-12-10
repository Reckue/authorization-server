package com.reckue.oauth.utils;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlreadyExistsUtil {

    public static void checkThrowReckuePasswordCredentialsAlreadyExists(Executable executable) {
        checkThrowReckueExceptionWithMessage("Password credentials already exists.", executable);
    }

    public static void checkThrowReckueExceptionWithMessage(String expectedMessage, Executable executable) {
        Exception exception = assertThrows(RuntimeException.class, executable);
        checkMessage(expectedMessage, exception);
    }

    private static void checkMessage(String expected, Exception actual) {
        assertEquals(expected, actual.getMessage());
    }
}

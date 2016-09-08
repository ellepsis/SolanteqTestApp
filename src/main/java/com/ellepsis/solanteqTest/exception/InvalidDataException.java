package com.ellepsis.solanteqTest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ellepsis on 08.09.2016.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid data")
public class InvalidDataException extends WebAppException {
    public InvalidDataException(String message) {
        super(message);
    }
}
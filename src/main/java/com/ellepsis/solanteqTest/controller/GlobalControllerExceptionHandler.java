package com.ellepsis.solanteqTest.controller;

import com.ellepsis.solanteqTest.exception.ErrorInfo;
import com.ellepsis.solanteqTest.exception.WebAppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ellepsis on 08.09.2016.
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(WebAppException.class)
    public ResponseEntity<ErrorInfo> handle(WebAppException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception);
        return new ResponseEntity<>(errorInfo, errorInfo.getHttpStatus());
    }
}

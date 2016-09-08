package com.ellepsis.solanteqTest.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Annotation;

/**
 * Created by Ellepsis on 08.09.2016.
 */
public class ErrorInfo {
    private String type;
    private Integer code;
    private String message;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ErrorInfo(WebAppException exception) {
        Annotation responseStatus =
                AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
        httpStatus = (HttpStatus) AnnotationUtils.getValue(responseStatus, "value");

        type = httpStatus.getReasonPhrase();
        code = httpStatus.value();
        message = exception.getMessage();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}

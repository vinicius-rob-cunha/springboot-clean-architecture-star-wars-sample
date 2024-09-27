package br.com.vroc.starwars.application.exception;

import org.springframework.http.HttpStatus;

public class BusinessValidationException extends ApiException {

    public BusinessValidationException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, ErrorType.BUSINESS, message);
    }

}

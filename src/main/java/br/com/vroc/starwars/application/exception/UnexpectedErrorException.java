package br.com.vroc.starwars.application.exception;

import org.springframework.http.HttpStatus;

public class UnexpectedErrorException extends ApiException {

    public UnexpectedErrorException(Throwable throwable) {
        this("An unexpected error occurred. Please try again later.", throwable);
    }

    public UnexpectedErrorException(String message, Throwable throwable) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, ErrorType.INTERNAL_SERVER_ERROR, null, throwable);
    }

}

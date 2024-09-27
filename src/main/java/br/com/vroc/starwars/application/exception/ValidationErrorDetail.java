package br.com.vroc.starwars.application.exception;

public record ValidationErrorDetail(
    String field,
    String message
) {

}

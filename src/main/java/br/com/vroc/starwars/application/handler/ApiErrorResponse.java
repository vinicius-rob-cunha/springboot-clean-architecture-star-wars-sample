package br.com.vroc.starwars.application.handler;

import br.com.vroc.starwars.application.exception.ValidationErrorDetail;
import java.util.List;

public record ApiErrorResponse(
    String type,
    String message,
    List<ValidationErrorDetail> details
) {

}

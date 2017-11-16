package lk.sliit.transport.publicTransportService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dinukshakandasamanage on 11/15/17.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="The Token Reference was not found")
public class CardNotFoundException extends Exception {

    public CardNotFoundException() {
        super();
    }

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CardNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

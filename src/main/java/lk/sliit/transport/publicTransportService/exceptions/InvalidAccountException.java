package lk.sliit.transport.publicTransportService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dinukshakandasamanage on 11/16/17.
 */

@ResponseStatus(value= HttpStatus.CONFLICT, reason="No passenger was found associated to this account")
public class InvalidAccountException extends Exception {
    public InvalidAccountException() {
        super();
    }

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountException(Throwable cause) {
        super(cause);
    }

    protected InvalidAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

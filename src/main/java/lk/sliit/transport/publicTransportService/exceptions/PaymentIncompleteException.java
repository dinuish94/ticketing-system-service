package lk.sliit.transport.publicTransportService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when the payment is not completed successfully.
 *
 * Created by dinukshakandasamanage on 11/19/17.
 */
@ResponseStatus(value= HttpStatus.CONFLICT, reason="No passenger was found associated to this account")
public class PaymentIncompleteException extends Exception{

    public PaymentIncompleteException() {
        super();
    }

    public PaymentIncompleteException(String message) {
        super(message);
    }

    public PaymentIncompleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentIncompleteException(Throwable cause) {
        super(cause);
    }

    protected PaymentIncompleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

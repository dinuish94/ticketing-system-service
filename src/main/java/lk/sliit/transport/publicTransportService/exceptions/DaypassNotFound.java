package lk.sliit.transport.publicTransportService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dinukshakandasamanage on 11/20/17.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No day pass found for this card")
public class DaypassNotFound extends Exception{
}

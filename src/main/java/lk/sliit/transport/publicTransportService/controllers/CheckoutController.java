package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.exceptions.CardNotFoundException;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
import lk.sliit.transport.publicTransportService.exceptions.PaymentIncompleteException;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles journey checkouts
 *
 * Created by dinukshakandasamanage on 11/13/17.
 */

@RestController
@RequestMapping(path = "/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    @Autowired
    private TripService tripService;
    Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    @PostMapping("/")
    @ResponseBody()
    public Trip checkout(@RequestBody TripDTO tripDTO) throws InvalidDataException {
        logger.info("Checking out trip " + tripDTO.getId() + "... ");
        return tripService.checkout(tripDTO);
    }

    @PostMapping("")
    public Trip confirmPaymentByCash(@RequestParam("tripId") long tripId) throws InvalidDataException {
        logger.info("Confirming payment by cash " + tripId + "... ");
        return tripService.confirmPayment(tripId);
    }
}

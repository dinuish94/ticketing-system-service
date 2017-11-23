package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;

import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;

import lk.sliit.transport.publicTransportService.exceptions.DaypassNotFound;

import lk.sliit.transport.publicTransportService.models.Daypass;
import lk.sliit.transport.publicTransportService.services.DaypassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jonathan on 11/15/2017.
 */

@RestController
@RequestMapping(path = "/daypasses")
@CrossOrigin(origins = "*")
public class DaypassController {

    @Autowired
    DaypassService daypassService;

    Logger logger = LoggerFactory.getLogger(DaypassController.class);

    @PostMapping("")
    @ResponseBody()
    public ResponseEntity<String> addDayPass(@RequestBody DaypassDTO daypassDTO) throws InvalidDataException {
        logger.info("Checking card ref of day pass" + daypassDTO.getCardRef() + "... ");
        return daypassService.addDayPass(daypassDTO);
    }

    @GetMapping("")
    @ResponseBody
    public boolean validateToken(@RequestParam("token") String token) throws DaypassNotFound {
        return daypassService.getDayPassForCard(token);
    }
}

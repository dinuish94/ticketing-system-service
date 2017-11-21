package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;
import lk.sliit.transport.publicTransportService.exceptions.DaypassNotFound;
import lk.sliit.transport.publicTransportService.models.Daypass;
import lk.sliit.transport.publicTransportService.services.DaypassService;
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

    @PostMapping("")
    @ResponseBody()
    public ResponseEntity<String> addDayPass(@RequestBody DaypassDTO daypassDTO){
        return daypassService.addDayPass(daypassDTO);
    }

    @GetMapping("")
    @ResponseBody
    public boolean validateToken(@RequestParam("token") String token) throws DaypassNotFound {
        return daypassService.getDayPassForCard(token);
    }
}

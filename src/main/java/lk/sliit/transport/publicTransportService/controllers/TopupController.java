package lk.sliit.transport.publicTransportService.controllers;
import lk.sliit.transport.publicTransportService.dtos.TopupDTO;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
import lk.sliit.transport.publicTransportService.services.TopupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jonathan on 11/15/2017.
 */
@RestController
@RequestMapping(path = "/topups")
@CrossOrigin(origins = "*")
public class TopupController {

    @Autowired
    TopupService topupService;

    Logger logger = LoggerFactory.getLogger(TopupController.class);

    @PostMapping("")
    @ResponseBody()
    public ResponseEntity<String> addDayPass(@RequestBody TopupDTO topupDTO) throws InvalidDataException {
        logger.info("Checking card ref of topup" + topupDTO.getCardRef() + "... ");
        return topupService.addTopup(topupDTO);
    }
}

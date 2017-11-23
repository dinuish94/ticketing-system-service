package lk.sliit.transport.publicTransportService.controllers;
import lk.sliit.transport.publicTransportService.dtos.TopupDTO;
import lk.sliit.transport.publicTransportService.services.TopupService;
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

    @PostMapping("")
    @ResponseBody()
    public ResponseEntity<String> addDayPass(@RequestBody TopupDTO topupDTO){
        return topupService.addTopup(topupDTO);
    }

}

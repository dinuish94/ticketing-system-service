package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/13/17.
 */

@RestController
@RequestMapping(path = "/cards")
@CrossOrigin(origins = "*")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/")
    @ResponseBody()
    public List<Card> getAllCards(){
        List<Card> cards = new ArrayList<>();
        cardRepository.findAll().forEach(card -> {
            cards.add(card);
        });
        return cards;
    }
}

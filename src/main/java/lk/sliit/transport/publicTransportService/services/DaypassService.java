package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;
import lk.sliit.transport.publicTransportService.exceptions.DaypassNotFound;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Daypass;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.DaypassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jonathan on 11/15/2017.
 */
@Service
public class DaypassService {

    @Autowired
    DaypassRepository daypassRepository;

    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<String> addDayPass(DaypassDTO daypassDTO){
        Daypass daypass = new Daypass();
        Card card = cardRepository.findByTokenRef(daypassDTO.getCardRef());
        daypass.setDate(daypassDTO.getPassDate());
        daypass.setCard(card);
        card.getDaypasses().add(daypassRepository.save(daypass));
        cardRepository.save(card);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public boolean getDayPassForCard(String token){
        Card card = cardRepository.findByTokenRef(token);

        if (null != card.getDaypasses()){

            for (Daypass daypass : card.getDaypasses()){
                Date daypassDate = daypass.getDate();
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(new Date());
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(daypassDate);

                return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                        cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            }
        }
        return false;
    }
}

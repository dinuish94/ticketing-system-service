package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 11/15/2017.
 */
@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers(){
        List<Passenger> passengers = new ArrayList<>();
        passengerRepository.findAll().forEach(passenger->{
            passengers.add(passenger);
        });
        return passengers;
    }

}

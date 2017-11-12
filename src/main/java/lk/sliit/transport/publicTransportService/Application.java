package lk.sliit.transport.publicTransportService;

import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.models.Visitor;
import lk.sliit.transport.publicTransportService.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dinukshakandasamanage on 11/11/17.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    PassengerRepository passengerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Passenger passenger = new Visitor();
        passenger.setName("Visitor 1");

        passengerRepository.save(passenger);

        passengerRepository.findAll().forEach(passenger1 -> {
            System.out.println(passenger1.toString());
        });
    }
}
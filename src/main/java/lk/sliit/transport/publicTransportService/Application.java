package lk.sliit.transport.publicTransportService;

import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.*;
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

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

    @Autowired
    BusCategoryRepository busCategoryRepository;

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Card card4 = new Card();
        card4.setBalance(50);
        System.out.println(cardRepository.save(card4).getTokenRef());

        Visitor passenger = new Visitor();
        passenger.setName("Visitor 1");
        passenger.setAddress("Negombo");
        passenger.setCard(card4);
        passengerRepository.save(passenger);

        Card card = new Card();
        card.setBalance(200);
        card.setVisitor(passenger);
        card = cardRepository.save(card);
        System.out.println(card.getTokenRef());

        passenger.setCard(card);
        passenger = passengerRepository.save(passenger);
        System.out.println(cardRepository.save(card).getTokenRef());

        Account account = new Account();
        account.setCard(card);
        account.setRole(1);
        account.setUsername("Bruce");
        account.setPassword("123");
        accountRepository.save(account);

        DailyPassenger passenger2 = new DailyPassenger();
        passenger2.setName("Bruce Wayne");
        passenger2.setAddress("Gotham");
        passenger2.setAccount(account);
        passenger2.setEmail("bruce@gmail.com");
        passengerRepository.save(passenger2);

        Card card2 = new Card();
        card2.setBalance(300);
        System.out.println(cardRepository.save(card2).getTokenRef());

        Account account2 = new Account();
        account2.setCard(card2);
        account2.setRole(1);
        account2.setUsername("Jonathan");
        account2.setPassword("123");
        accountRepository.save(account2);

        DailyPassenger passenger3 = new DailyPassenger();
        passenger3.setName("Jonathan");
        passenger3.setAddress("Colombo");
        passenger3.setAccount(account2);
        passenger3.setEmail("jonathan@gmail.com");
        passengerRepository.save(passenger3);

        passengerRepository.findAll().forEach(passenger1 -> {
            System.out.println(passenger1.toString());
        });

        BusCategory busCategory = new BusCategory();
        busCategory.setRate(20);
        busCategory.setType("Luxury");
        busCategory = busCategoryRepository.save(busCategory);

        Bus bus = new Bus();
        bus.setBusCategory(busCategory);
        bus = busRepository.save(bus);
        System.out.println(bus.getId());

        BusStop busStop = new BusStop();
        busStop.setLocation("Kolpitty");
        System.out.println(busStopRepository.save(busStop));

//        System.out.println(busRepository.findOne(bus.getId()).getId());
    }
}
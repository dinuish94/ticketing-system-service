package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long>{
}

package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.BusStop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinukshakandasamanage on 11/13/17.
 */

@Repository
public interface BusStopRepository extends CrudRepository<BusStop, Long> {

    /**
     * Retrieves bus stop by location
     * @param location
     * @return
     */
    BusStop findByLocation(String location);
}

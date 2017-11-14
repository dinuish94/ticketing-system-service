package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinukshakandasamanage on 11/13/17.
 */

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    Card findByTokenRef(String tokenRef);
}

package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.BusCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinukshakandasamanage on 11/14/17.
 */
@Repository
public interface BusCategoryRepository extends CrudRepository<BusCategory,Long>{
}

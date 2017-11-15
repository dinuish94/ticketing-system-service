package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jonathan on 11/15/2017.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);
}

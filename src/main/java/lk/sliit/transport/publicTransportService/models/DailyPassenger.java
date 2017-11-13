package lk.sliit.transport.publicTransportService.models;

import javax.persistence.*;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class DailyPassenger extends Passenger {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

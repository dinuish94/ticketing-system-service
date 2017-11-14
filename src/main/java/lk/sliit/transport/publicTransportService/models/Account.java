package lk.sliit.transport.publicTransportService.models;

import javax.persistence.*;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String username;
    private String password;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, optional = false)
    private DailyPassenger dailyPassenger;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DailyPassenger getDailyPassenger() {
        return dailyPassenger;
    }

    public void setDailyPassenger(DailyPassenger dailyPassenger) {
        this.dailyPassenger = dailyPassenger;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

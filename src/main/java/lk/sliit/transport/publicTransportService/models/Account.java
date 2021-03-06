package lk.sliit.transport.publicTransportService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, optional = false)
    @JsonIgnore
    private DailyPassenger dailyPassenger;

    @OneToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
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

package lk.sliit.transport.publicTransportService.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dinukshakandasamanage on 11/14/17.
 */

@Entity
public class Daypass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

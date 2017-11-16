package lk.sliit.transport.publicTransportService.dtos;

import java.util.Date;

/**
 * Created by Jonathan on 11/15/2017.
 */
public class DaypassDTO {
    private String cardRef;
    private Date passDate;

    public String getCardRef() {
        return cardRef;
    }

    public void setCardRef(String cardRef) {
        this.cardRef = cardRef;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }
}

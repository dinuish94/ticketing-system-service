package lk.sliit.transport.publicTransportService.dtos;

/**
 * Created by Jonathan on 11/15/2017.
 */
public class TopupDTO {
    private String cardRef;
    private double amount;

    public String getCardRef() {
        return cardRef;
    }

    public void setCardRef(String cardRef) {
        this.cardRef = cardRef;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

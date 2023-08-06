package Models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
    private int referenceNumber;
    private Ticket ticketDetails;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
}


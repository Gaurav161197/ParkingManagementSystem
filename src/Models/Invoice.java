package Models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class Invoice {
    private String invoiceId;
    private Payment paymentDetails;
    private double amount;
    private LocalDateTime invoiceGenerationTime;
}

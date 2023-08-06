package DTOs;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReceiptResponseObject {
    private String receiptNumber;
    private double amount;
    private LocalDateTime receiptTime;
}

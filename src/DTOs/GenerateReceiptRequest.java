package DTOs;


import Models.ParkingGate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateReceiptRequest {
    private int ticketNumber;
    private ParkingGate parkingGateDetails;
}

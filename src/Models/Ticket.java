package Models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {

    private int ticketNumber;
    private ParkingGate entryGateDetails;
    private LocalDateTime entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpotDetails;
}

package DTOs;

import Models.ParkingGate;
import Models.Vehicle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateTicketRequest {
    private  Vehicle vehicleInfo;
    private ParkingGate parkingGateInfo;
}

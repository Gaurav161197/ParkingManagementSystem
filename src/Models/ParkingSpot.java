package Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSpot {
    private int floorNumber;
    private ParkingSpotStatus status;
    private VehicleType parkingSpotType;

}


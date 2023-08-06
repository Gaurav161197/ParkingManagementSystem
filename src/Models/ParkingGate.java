package Models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingGate {
    private int gateNumber;
    private GateType gateType;
    private ParkingAttendant parkingAttendantDetails;
}




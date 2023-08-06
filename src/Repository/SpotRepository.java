package Repository;

import Models.ParkingSpot;
import Models.ParkingSpotStatus;
import Models.VehicleType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpotRepository {


   public static List<ParkingSpot> parkingSpotsList = new ArrayList<>();

    public static void initializeSpots() {
        ParkingSpot spot1 = ParkingSpot.builder().parkingSpotType(VehicleType.BUS).
                floorNumber(1).status(ParkingSpotStatus.FREE).build();
        ParkingSpot spot2 = ParkingSpot.builder().parkingSpotType(VehicleType.CAR).
                floorNumber(2).status(ParkingSpotStatus.FREE).build();
        ParkingSpot spot3 = ParkingSpot.builder().parkingSpotType(VehicleType.CAR).
                floorNumber(1).status(ParkingSpotStatus.FREE).build();
        ParkingSpot spot4 = ParkingSpot.builder().parkingSpotType(VehicleType.BIKE).
                floorNumber(1).status(ParkingSpotStatus.FREE).build();

        parkingSpotsList.addAll(Arrays.asList(spot1, spot2, spot3, spot4));
    }

    public Optional<ParkingSpot> findAvailableSpot(VehicleType vehicleType) {
        Optional<ParkingSpot> firstMatchingSpot = parkingSpotsList.stream()
                .filter(spot -> spot.getParkingSpotType().equals(vehicleType) &&
                        spot.getStatus().equals(ParkingSpotStatus.FREE))
                .findFirst();
        return firstMatchingSpot;

    }

    public ParkingSpot updateSpotAvailability(ParkingSpot parkingSpot) {
        parkingSpot.setStatus(ParkingSpotStatus.BLOCKED);
        return parkingSpot;
    }


}

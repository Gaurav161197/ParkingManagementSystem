package Services;

import Models.ParkingSpot;
import Models.VehicleType;
import Repository.SpotRepository;

import java.util.Optional;

public class SpotManagementService {

    public ParkingSpot findAndBookParkingSpot(VehicleType vehicleType) {
        SpotRepository spotRepository = new SpotRepository();
        Optional<ParkingSpot> spot = spotRepository.findAvailableSpot(vehicleType);

        if (!spot.isPresent())
            throw new RuntimeException("No Available Slot for vehicle type: " + vehicleType.toString());

        ParkingSpot parkingSpot = spot.get();
        return spotRepository.updateSpotAvailability(parkingSpot);

    }
}

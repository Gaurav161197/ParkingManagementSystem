package Strategies;

import Models.VehicleType;

public class CostCalculationStrategyFactory {

    public static CostCalculationStrategy getCostCalculationStrategy(VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.BIKE))
            return new BikeCostCalculationStrategy();
        else if (vehicleType.equals(VehicleType.BUS))
            return new BusCostCalculationStrategy();
        else if (vehicleType.equals(VehicleType.CAR))
            return new CarCostCalculationStrategy();

        return null;
    }
}

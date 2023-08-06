package Strategies;

import Utils.PaymentUtils;

import java.time.Duration;
import java.time.LocalDateTime;

public class BusCostCalculationStrategy implements CostCalculationStrategy {
    @Override
    public double calculateCost(LocalDateTime inTime) {
        //getting random future out time
        LocalDateTime outTime = PaymentUtils.getFutureDateTime();
        Duration duration = Duration.between(inTime, outTime);
        long seconds = duration.toDays();
        return (double) seconds * (500);
    }
}

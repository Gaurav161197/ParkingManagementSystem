package Strategies;

import java.time.LocalDateTime;

public interface CostCalculationStrategy {
    public double calculateCost(LocalDateTime inTime);
}

package Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

public class PaymentUtils {

    public static LocalDateTime getFutureDateTime() {
        // Step 1: Get the current LocalDateTime
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Step 2: Generate a random number of seconds
        long maxSecondsInTheFuture = 365 * 24 * 60 * 60; // 1 year (365 days) worth of seconds
        long randomSeconds = ThreadLocalRandom.current().nextLong(0, maxSecondsInTheFuture);

        // Step 3: Add the random number of seconds to the current LocalDateTime
        LocalDateTime futureDateTime = currentDateTime.plus(randomSeconds, ChronoUnit.SECONDS);

        return futureDateTime;
    }
}

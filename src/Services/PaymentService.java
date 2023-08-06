package Services;

import Models.*;
import Strategies.CostCalculationStrategy;
import Strategies.CostCalculationStrategyFactory;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;


public class PaymentService {

    private CostCalculationStrategy costCalculationStrategy;

    public Payment performPayment(Ticket ticketDetails) {

        //based on the vehicle type, calculating parking cost
        VehicleType vehicleType = ticketDetails.getVehicle().getVehicleType();
        this.costCalculationStrategy = CostCalculationStrategyFactory.getCostCalculationStrategy(vehicleType);

        double amount = costCalculationStrategy.calculateCost(ticketDetails.getEntryTime());


        //getting from user, the mode of payment
        System.out.printf("Your total amount is Rs. %s, please enter mode of payment: CASH/CARD/UPI", amount);
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String paymentMode = sc.next();

        PaymentMethod paymentMethod = null;

        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.toString().equals(paymentMode.toUpperCase())) {
                paymentMethod = method;
                break;
            }
        }

        if (paymentMethod == null)
            throw new RuntimeException("Invalid mode of payment received");

        //based on mode of payment, transaction will be done through 3rd party apis and in return we will receive payment status.
        PaymentStatus paymentStatus = performTransaction(amount, paymentMethod);

        if (paymentStatus.equals(PaymentStatus.COMPLETED))
            System.out.println("Payment is successful for vehicle " + ticketDetails.getVehicle().getRegistrationNumber());
        else
            System.out.println("Payment is unsuccessful for vehicle " + ticketDetails.getVehicle().getRegistrationNumber());


        //setting parking spot status back to free
        ticketDetails.getParkingSpotDetails().setStatus(ParkingSpotStatus.FREE);

        //returning the payment details, we can store the transaction and payment details within DB
        return Payment.builder()
                .paymentMethod(paymentMethod).paymentStatus(paymentStatus)
                .referenceNumber(new Random().nextInt(50000))
                .amount(amount).
                ticketDetails(ticketDetails).build();
    }

    public PaymentStatus performTransaction(Double amount, PaymentMethod paymentMethod) {
        return PaymentStatus.COMPLETED;
    }


    //generating receipt for a successful payment
    public Invoice generateInvoice(Payment payment) {
        return Invoice.builder()
                .invoiceId(String.valueOf(new Random().nextInt(30000)))
                .amount(payment.getAmount())
                .paymentDetails(payment)
                .invoiceGenerationTime(LocalDateTime.now()).build();

    }
}

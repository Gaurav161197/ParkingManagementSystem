package Controllers;

import DTOs.GenerateReceiptRequest;
import DTOs.GenerateTicketRequest;
import DTOs.ReceiptResponseObject;
import Models.Invoice;
import Models.Payment;
import Models.PaymentStatus;
import Models.Ticket;
import Services.PaymentService;
import Services.TicketService;

public class EntryExitController {


    private TicketService ticketService;
    private PaymentService paymentService;


    public EntryExitController(TicketService ticketService, PaymentService paymentService) {
        this.ticketService = ticketService;
        this.paymentService = paymentService;
    }

    public Ticket generateTicket(GenerateTicketRequest generateTicketRequest) {

        if (generateTicketRequest.getVehicleInfo().equals(null)
                || generateTicketRequest.getParkingGateInfo().equals(null))
            throw new IllegalArgumentException("Please provide input in correct format");

        Ticket ticket = ticketService.generateTicket(generateTicketRequest.getParkingGateInfo(), generateTicketRequest.getVehicleInfo());

        return ticket;
    }

    public ReceiptResponseObject generateReceipt(GenerateReceiptRequest generateReceiptRequest) {

        Ticket ticketDetails = ticketService.getTicketDetails(generateReceiptRequest.getTicketNumber());

        //initiating payment after getting the ticket details
        Payment payment = paymentService.performPayment(ticketDetails);

        //generating invoice
        System.out.println("Invoice is getting generated for payment id: " + payment.getReferenceNumber());
        Invoice invoice = paymentService.generateInvoice(payment);


        //Generating receipt response object need by client
        return ReceiptResponseObject.builder().receiptNumber(invoice.getInvoiceId())
                .receiptTime(invoice.getInvoiceGenerationTime())
                .amount(invoice.getAmount())
                .build();

    }
}

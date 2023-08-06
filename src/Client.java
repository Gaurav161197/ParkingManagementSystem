import Controllers.EntryExitController;
import DTOs.GenerateReceiptRequest;
import DTOs.GenerateTicketRequest;
import DTOs.ReceiptResponseObject;
import Models.*;
import Repository.SpotRepository;
import Repository.TicketRepository;
import Services.PaymentService;
import Services.SpotManagementService;
import Services.TicketService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Client {
    public static void main(String[] args) {


        //creating  objects for controller and services.
        SpotManagementService spotManagementService = new SpotManagementService();
        TicketRepository ticketRepository = new TicketRepository();
        TicketService ticketService = new TicketService(spotManagementService, ticketRepository);
        PaymentService paymentService = new PaymentService();
        EntryExitController entryExitController = new EntryExitController(ticketService, paymentService);


        //initializing parking spots
        SpotRepository.initializeSpots();

        // Generating 5 vehicles object
        Vehicle vehicle1 = Vehicle.builder().vehicleType(VehicleType.BUS).registrationNumber("123").build();
        Vehicle vehicle2 = Vehicle.builder().vehicleType(VehicleType.BUS).registrationNumber("456").build();
        Vehicle vehicle3 = Vehicle.builder().vehicleType(VehicleType.CAR).registrationNumber("789").build();
        Vehicle vehicle4 = Vehicle.builder().vehicleType(VehicleType.CAR).registrationNumber("101").build();
        Vehicle vehicle5 = Vehicle.builder().vehicleType(VehicleType.BIKE).registrationNumber("102").build();


        //creating  parking Gate object with parkingAttendant
        ParkingAttendant parkingAttendant = ParkingAttendant.builder().id("parkingAttendant1").name("Aditi").build();
        ParkingGate parkingGate = ParkingGate.builder().gateNumber(1)
                .gateType(GateType.ENTRY).parkingAttendantDetails(parkingAttendant).build();

        List<GenerateTicketRequest> ticketRequests = new ArrayList<>();

        GenerateTicketRequest generateTicketRequest = GenerateTicketRequest.builder()
                .parkingGateInfo(parkingGate)
                .vehicleInfo(vehicle1).build();
        GenerateTicketRequest generateTicketRequest2 = GenerateTicketRequest.builder()
                .parkingGateInfo(parkingGate)
                .vehicleInfo(vehicle3).build();
        GenerateTicketRequest generateTicketRequest3 = GenerateTicketRequest.builder()
                .parkingGateInfo(parkingGate)
                .vehicleInfo(vehicle4).build();
        GenerateTicketRequest generateTicketRequest4 = GenerateTicketRequest.builder()
                .parkingGateInfo(parkingGate)
                .vehicleInfo(vehicle5).build();

        ticketRequests.addAll(Arrays.asList(generateTicketRequest, generateTicketRequest2, generateTicketRequest3));


        //generating 3 requests and calling our request handler
        for (GenerateTicketRequest request : ticketRequests) {
            Ticket ticket = entryExitController.generateTicket(request);
            System.out.printf("Ticket is generated successfully for vehicle  %s , here are the ticket details: %s",
                    ticket.getVehicle()
                            .getRegistrationNumber(), ticket.toString());
            System.out.println();
        }

        //printing current SPOT status
        System.out.println("Current parking spot details: ");
        System.out.println(SpotRepository.parkingSpotsList.toString());


        //2nd api for generating receipt

        List<Ticket> allTicketList = ticketRepository.getAllTickets();

        //creating exit parking Gate object with parkingAttendant
        ParkingAttendant parkingAttendant2 = ParkingAttendant.builder().id("parkingAttendant2").name("Sakshi").build();
        ParkingGate parkingGate2 = ParkingGate.builder().gateNumber(2)
                .gateType(GateType.EXIT).parkingAttendantDetails(parkingAttendant2).build();


        //generating receipt for all tickets received so far.
        for (Ticket ticket : allTicketList) {
            GenerateReceiptRequest generateReceiptRequest = GenerateReceiptRequest.builder()
                    .ticketNumber(ticket.getTicketNumber())
                    .parkingGateDetails(parkingGate2)
                    .build();

            ReceiptResponseObject receiptResponseObject = entryExitController.generateReceipt(generateReceiptRequest);
            System.out.println("Here is your receipt: ");
            System.out.println(receiptResponseObject.toString());
        }

        //printing current SPOT status
        System.out.println("Current parking spot details: ");
        System.out.println(SpotRepository.parkingSpotsList.toString());


    }
}

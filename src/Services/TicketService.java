package Services;

import Models.ParkingGate;
import Models.ParkingSpot;
import Models.Ticket;
import Models.Vehicle;
import Repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.Random;

public class TicketService {

    private SpotManagementService spotManagementService;
    private TicketRepository ticketRepository;

    public TicketService(SpotManagementService spotManagementService,TicketRepository ticketRepository) {
        this.spotManagementService = spotManagementService;
        this.ticketRepository= ticketRepository;
    }

    public Ticket generateTicket(ParkingGate gate, Vehicle vehicle) {
        ParkingSpot bookedSpot = spotManagementService.findAndBookParkingSpot(vehicle.getVehicleType());
        LocalDateTime currentTime= LocalDateTime.now();

        int randomNumber= new Random().nextInt();

        Ticket ticket = Ticket.builder()
                .ticketNumber(randomNumber)
                .vehicle(vehicle)
                .entryGateDetails(gate)
                .entryTime(currentTime).
                parkingSpotDetails(bookedSpot)
                .build();

        ticketRepository.addTicketToDB(ticket);
        return ticket;
    }

    public Ticket getTicketDetails(int ticketNumber) {
       return ticketRepository.getTicketByTicketNumber(ticketNumber);
    }
}

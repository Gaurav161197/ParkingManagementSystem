package Repository;

import Models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    List<Ticket> ticketList = new ArrayList<>();

    public void addTicketToDB(Ticket ticket) {
        ticketList.add(ticket);
    }

    public Ticket getTicketByTicketNumber(int ticketNumber) {
        return ticketList.stream().filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst().orElse(null);

    }
    public List<Ticket> getAllTickets(){
        return  this.ticketList;
    }
}

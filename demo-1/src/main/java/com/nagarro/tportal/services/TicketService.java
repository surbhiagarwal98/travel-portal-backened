package com.nagarro.tportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.tportal.model.Ticket;
import com.nagarro.tportal.repositories.TicketRepository;

/**
 * interacts with ticket repository for all ticket related operations
 * 
 * @author surbhiagarwal
 *
 */
@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	/**
	 * gets all the tickets
	 * 
	 * @return
	 */
	public List<Ticket> getAllTickets() {

		List<Ticket> tickets = ticketRepository.findAll();// .forEach(tickets::add);
		return tickets;
	}

	/**
	 * saves the ticket in database
	 * 
	 * @param ticket
	 */
	public void saveTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	/**
	 * edits ticket
	 * 
	 * @param ticket
	 */
	public void editTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}

}

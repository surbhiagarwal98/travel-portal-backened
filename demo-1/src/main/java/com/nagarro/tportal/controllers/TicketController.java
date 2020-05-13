package com.nagarro.tportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.tportal.model.Ticket;
import com.nagarro.tportal.model.User;
import com.nagarro.tportal.services.LoginSecurityService;
import com.nagarro.tportal.services.MyUserDetailsService;
import com.nagarro.tportal.services.TicketService;

/**
 * ticket controller
 * 
 * @author surbhiagarwal
 *
 */

@RestController
public class TicketController {

	@Autowired
	private TicketService ticketService;
	@Autowired
	private LoginSecurityService loginSecurityService;
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	/**
	 * changes ticket status
	 * 
	 * @param ticket
	 */

	@RequestMapping(value = "/admin/ticketStatus", method = RequestMethod.PUT)
	public void changeTicketStatus(@RequestBody Ticket ticket) {
		if (loginSecurityService.isAdmin()) {
			ticketService.editTicket(ticket);
		}
	}

	/**
	 * edits ticket
	 * 
	 * @param ticket
	 * @return
	 */

	@RequestMapping(value = "/users/tickets", method = RequestMethod.PUT)
	@ResponseBody
	public String editTicket(@RequestBody Ticket ticket) {
		String message = null;
		if (ticket.getTicketStatus().equalsIgnoreCase("in process")) {
			message = "{\"message\":\"Processing ticket can not be edited!\"}";
		} else {
			try {
				String username = loginSecurityService.getCurrentUser();
				User user = myUserDetailsService.getUser(username);
				ticket.setUser(user);
				ticket.setTicketStatus("re-submitted");
				ticketService.editTicket(ticket);
			} catch (Exception e) {
				message = "{\"message\":\"Couldn't edit ticket!\"}";
			}
			message = "{\"message\":\"Ticket edit successfully\"}";
		}
		return message;
	}

	/**
	 * submits new ticket to database
	 * 
	 * @param ticket
	 * @return
	 */

	@RequestMapping(value = "/users/tickets", method = RequestMethod.POST)
	@ResponseBody
	public String submitTicket(@RequestBody Ticket ticket) {
		String message = null;
		String username = loginSecurityService.getCurrentUser();
		User user = myUserDetailsService.getUser(username);
		ticket.setUser(user);
		ticket.setTicketStatus("submitted");
		try {
			ticketService.saveTicket(ticket);
		} catch (Exception e) {
			message = "{\"message\":\"couldn't save ticket\"}";
		}
		message = "{\"message\":\"Ticket submitted successfully\"}";
		return message;

	}

	/**
	 * fetches user specific tickets
	 * 
	 * @return
	 */

	@RequestMapping(value = "/users/tickets", method = RequestMethod.GET)
	public List<Ticket> getUserTickets() {
		String username = loginSecurityService.getCurrentUser();
		User user = myUserDetailsService.getUser(username);
		List<Ticket> tickets = user.getTickets();
		return tickets;

	}

}

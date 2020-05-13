package com.nagarro.tportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.tportal.Util.JwtUtil;
import com.nagarro.tportal.dto.AuthenticationRequest;
import com.nagarro.tportal.dto.AuthenticationResponse;
import com.nagarro.tportal.model.Ticket;
import com.nagarro.tportal.services.LoginSecurityService;
import com.nagarro.tportal.services.MyUserDetailsService;
import com.nagarro.tportal.services.TicketService;

/**
 * Admin controller
 * @author surbhiagarwal
 *
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public AuthenticationManager authenticationManager;

	@Autowired
	public MyUserDetailsService myUserDetailsService;

	@Autowired
	private LoginSecurityService loginSecurityService;

	@Autowired
	private TicketService ticketService;

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	/**
	 * authenticates admin
	 * @param authenticationRequest
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException bce) {
			throw new Exception("Invalid Username or password");
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = JwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	/**
	 * retuns all tickers from Database
	 * @return
	 */
	@RequestMapping("/tickets")
	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = null;
		if (loginSecurityService.isAdmin()) {
			tickets = ticketService.getAllTickets();
		}
		return tickets;
	}

}

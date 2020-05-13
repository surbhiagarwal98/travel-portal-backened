package com.nagarro.tportal.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.tportal.Util.JwtUtil;
import com.nagarro.tportal.Util.RandomizePasswordUtil;
import com.nagarro.tportal.constants.Constants;
import com.nagarro.tportal.dto.AuthenticationRequest;
import com.nagarro.tportal.dto.AuthenticationResponse;
import com.nagarro.tportal.model.User;
import com.nagarro.tportal.services.EmailService;
import com.nagarro.tportal.services.MyUserDetailsService;

/**
 * user controller
 * 
 * @author surbhiagarwal
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private EmailService emailService;

	/**
	 * a hello method to check working
	 * 
	 * @return
	 */

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}

	/**
	 * authenticates user
	 * 
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
	 * registers new user
	 * 
	 * @param user
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String registerUser(@RequestBody User user) throws MessagingException {
		String message = null;
		String username = user.getEmail();
		String password = RandomizePasswordUtil.generatePassword();
		String email = user.getEmail();

		user.setUsername(user.getEmail());
		user.setPassword(password);
		myUserDetailsService.registerUser(user);
		String text = "<b>Hello " + user.getFirstName()
				+ ",</b><br><br>You have requested your login credentials for your "
				+ "access to the Nagarro Travel Portal:<br> Username: " + username + "<br>Password: " + password
				+ "<br><br>Please contact the Travel team if you have any questions."
				+ "<br><br>Thankyou<br>Nagarro Travel Team";

		String subject = Constants.EMAIL_SUBJECT;
		try {
			emailService.sendEmail(text, subject, email);
		} catch (Exception e) {
			message = "An error occurred!";
		}
		message = "Registration successful!..Kindly check your email.";
		return message;

	}

	/**
	 * gets the forgotten password
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/getPassword", method = RequestMethod.POST)
	@ResponseBody
	public String forgotPassword(@RequestBody String email) {

		User user = myUserDetailsService.getUser(email);

		String text = "<b>Hello" + user.getFirstName()
				+ ",<b><br><br>You have requested your login credentials for your "
				+ "access to the Nagarro Travel Portal:<br> Username: " + user.getUsername() + "<br>Password: "
				+ user.getPassword() + "<br><br>Please contact the Travel team if you have any questions."
				+ "<br><br>Thankyou<br>Nagarro Travel Team";

		String subject = Constants.PASSWORD_REQUEST_SUBJECT;
		try {
			emailService.sendEmail(text, subject, email);
		} catch (Exception e) {
			System.out.println("An error occured");
		}
		return "You have received an email with your credentials.";
	}

}

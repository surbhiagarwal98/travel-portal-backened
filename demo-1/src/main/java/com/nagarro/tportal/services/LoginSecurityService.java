package com.nagarro.tportal.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * login security to check current valid authorised user and adnin
 * 
 * @author surbhiagarwal
 *
 */
@Service
public class LoginSecurityService {
	/**
	 * gets currently logged in user
	 * 
	 * @return
	 */
	public String getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	/**
	 * checks if the user logged in is admin or not
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean isAdmin() {
		boolean isAdmin = false;

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		if (authorities.size() != 0) {
			isAdmin = true;
		}
		return isAdmin;
	}
}

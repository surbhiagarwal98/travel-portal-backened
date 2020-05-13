package com.nagarro.tportal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.tportal.model.Admin;
import com.nagarro.tportal.model.User;
import com.nagarro.tportal.repositories.AdminRepository;
import com.nagarro.tportal.repositories.UserRepository;

/**
 * performs all user related services
 * 
 * @author surbhiagarwal
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;

	/**
	 * returns user details for given username
	 */

	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		Optional<User> optionalUser = userRepository.findById(username);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		} else {
			Optional<Admin> optionalAdmin = adminRepository.findById(username);
			Admin admin = optionalAdmin.get();
			userDetails = new org.springframework.security.core.userdetails.User(admin.getAdminId(),
					admin.getAdminPassword(), getAuthorities());
		}
		return userDetails;
	}

	/**
	 * gets the authority list
	 * 
	 * @return
	 */

	private List<GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		return authorities;
	}

	/**
	 * interacts with repository and saves user
	 * 
	 * @param user
	 */
	public void registerUser(User user) {
		userRepository.save(user);
	}

	/**
	 * gets the list of all users in database
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	/**
	 * gets user with given parameter
	 * 
	 * @param email
	 * @return
	 */
	public User getUser(String email) {
		Optional<User> optionalEntity = userRepository.findById(email);
		User user = optionalEntity.get();
		return user;
	}

}

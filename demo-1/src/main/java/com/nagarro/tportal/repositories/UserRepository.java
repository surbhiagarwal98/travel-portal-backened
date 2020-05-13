package com.nagarro.tportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.tportal.model.User;

/**
 * repository to interact with user table in database
 * 
 * @author surbhiagarwal
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

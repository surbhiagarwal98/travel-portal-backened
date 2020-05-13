package com.nagarro.tportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.tportal.model.Admin;

/**
 * repository to interact with Admin table in database
 * @author surbhiagarwal
 *
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

}

package com.nagarro.tportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.tportal.model.Ticket;

/**
 * repository to interact with ticket table in database
 * 
 * @author surbhiagarwal
 *
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

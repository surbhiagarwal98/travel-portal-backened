package com.nagarro.tportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * model class for ticket entity
 * 
 * @author surbhiagarwal
 *
 */
@Entity
@Table(name = "TicketDetails")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TICKET_ID")
	private long ticketId;

	@Column(name = "REQUEST_TYPE")
	private String requestType;

	@Column(name = "PRIORITY")
	private String priority;

	@Column(name = "LOCATION_TO")
	private String locationTo;

	@Column(name = "LOCATION_FROM")
	private String locationFrom;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "PASSPORT_NO")
	private String passportNo;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "EXPENSE_BEARER")
	private String expenseBearer;

	@Column(name = "TRAVEL_APPROVER")
	private String travelApprover;

	@Column(name = "EXPECTED_TRAVEL_DURATION")
	private String expectedTravelDuration;

	@Column(name = "MAX_ALLOWED_AMOUNT")
	private long maxAllowedAmount;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TICKET_STATUS")
	private String ticketStatus;

	@ManyToOne
	@JsonIgnore
	private User user;

	public Ticket() {
	}

	public Ticket(long ticketId, String requestType, String priority, String locationTo, String locationFrom,
			Date startDate, Date endDate, String passportNo, String projectName, String expenseBearer,
			String travelApprover, String expectedTravelDuration, long maxAllowedAmount, String description,
			String ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.requestType = requestType;
		this.priority = priority;
		this.locationTo = locationTo;
		this.locationFrom = locationFrom;
		this.startDate = startDate;
		this.endDate = endDate;
		this.passportNo = passportNo;
		this.projectName = projectName;
		this.expenseBearer = expenseBearer;
		this.travelApprover = travelApprover;
		this.expectedTravelDuration = expectedTravelDuration;
		this.maxAllowedAmount = maxAllowedAmount;
		this.description = description;
		this.ticketStatus = ticketStatus;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLocationTo() {
		return locationTo;
	}

	public void setLocationTo(String locationTo) {
		this.locationTo = locationTo;
	}

	public String getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(String locationFrom) {
		this.locationFrom = locationFrom;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getExpenseBearer() {
		return expenseBearer;
	}

	public void setExpenseBearer(String expenseBearer) {
		this.expenseBearer = expenseBearer;
	}

	public String getTravelApprover() {
		return travelApprover;
	}

	public void setTravelApprover(String travelApprover) {
		this.travelApprover = travelApprover;
	}

	public String getExpectedTravelDuration() {
		return expectedTravelDuration;
	}

	public void setExpectedTravelDuration(String expectedTravelDuration) {
		this.expectedTravelDuration = expectedTravelDuration;
	}

	public long getMaxAllowedAmount() {
		return maxAllowedAmount;
	}

	public void setMaxAllowedAmount(long maxAllowedAmount) {
		this.maxAllowedAmount = maxAllowedAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package com.nagarro.tportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * model class for Admin entity
 * 
 * @author surbhiagarwal
 *
 */
@Entity
@Table(name = "AdminDetails")
public class Admin {

	@Id
	@Column(name = "ADMIN_USERNAME")
	private String adminUsername;
	@Column(name = "ADMIN_PASSWORD")
	private String adminPassword;

	public Admin() {
	}

	public Admin(String adminUsername, String adminPassword) {
		super();
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}

	public String getAdminId() {
		return adminUsername;
	}

	public void setAdminId(String adminId) {
		this.adminUsername = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}

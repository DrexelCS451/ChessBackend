package com.example.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
public class DBUser implements java.io.Serializable {

	private int userId;
	private String firstname;
	private String lastname;

	public DBUser() {
	}

	public DBUser(int userId, String firstname, String lastname) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 5, scale = 0)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "firstname", nullable = false, length = 20)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", nullable = false, length = 20)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


}

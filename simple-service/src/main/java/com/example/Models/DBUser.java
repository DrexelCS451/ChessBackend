package com.example.Models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class DBUser implements java.io.Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 5, scale = 0)
	private int userId;

	@Column(name = "username", nullable = false, length = 20, unique = true)
	private String username;

	public DBUser(){}

	public DBUser(int userId, String username) {
		this.userId = userId;
		this.username = username;
	}
	public DBUser(String username){
		this.username = username;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "This user is "+username;
	}
}

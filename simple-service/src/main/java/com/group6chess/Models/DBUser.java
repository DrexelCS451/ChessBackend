package com.group6chess.Models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class DBUser implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;

	@Column(name = "username", nullable = false, length = 20, unique = true)
	private String username;

	public DBUser(){}

	public DBUser(int userId, String username) {
		this.id = userId;
		this.username = username;
	}
	public DBUser(String username){
		this.username = username;
	}

	public int getUserId() {
		return this.id;
	}

	public void setUserId(int userId) {
		this.id = userId;
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

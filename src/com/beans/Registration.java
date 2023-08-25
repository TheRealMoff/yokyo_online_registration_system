package com.beans;

public class Registration {
	
	private int id;
	private String email;
	private String username;
	private String password;
	private String favourite_sport;
	
	//default registration constructor
	public Registration() {
		super();
		
	}
	
	public Registration(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public Registration(int id, String email, String username, String password, String favourite_sport) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.favourite_sport = favourite_sport;
	}
	
	public Registration(String email, String username, String password, String favourite_sport) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.favourite_sport = favourite_sport;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFavourite_sport() {
		return favourite_sport;
	}
	public void setFavourite_sport(String favourite_sport) {
		this.favourite_sport = favourite_sport;
	}
	@Override
	public String toString() {
		return "Registration [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", favourite_sport=" + favourite_sport + "]";
	}
	
	

}

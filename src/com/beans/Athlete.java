package com.beans;

public class Athlete {
	
	int id;
	private String forename;
	private String surname;
	private String country;
	private String sport;
	private String username;
	private String password;
	public Athlete() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Athlete(String forename, String surname, String country, String sport, String username, String password) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.country = country;
		this.sport = sport;
		this.username = username;
		this.password = password;
	}
	public Athlete(int id, String forename, String surname, String country, String sport, String username,
			String password) {
		super();
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.country = country;
		this.sport = sport;
		this.username = username;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
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
	@Override
	public String toString() {
		return "Athlete [id=" + id + ", forename=" + forename + ", surname=" + surname + ", country=" + country
				+ ", sport=" + sport + ", username=" + username + ", password=" + password + "]";
	}
	
	
}

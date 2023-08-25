package com.beans;

public class Staff {
	
	int id;
	private String staff_forename;
	private String staff_surname;
	private String username;
	private String password;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(String staff_forename, String staff_surname, String username, String password) {
		super();
		this.staff_forename = staff_forename;
		this.staff_surname = staff_surname;
		this.username = username;
		this.password = password;
	}

	public Staff(int id, String staff_forename, String staff_surname, String username, String password) {
		super();
		this.id = id;
		this.staff_forename = staff_forename;
		this.staff_surname = staff_surname;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStaff_forename() {
		return staff_forename;
	}

	public void setStaff_forename(String staff_forename) {
		this.staff_forename = staff_forename;
	}

	public String getStaff_surname() {
		return staff_surname;
	}

	public void setStaff_surname(String staff_surname) {
		this.staff_surname = staff_surname;
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
		return "Staff [id=" + id + ", staff_forename=" + staff_forename + ", staff_surname=" + staff_surname
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	

}

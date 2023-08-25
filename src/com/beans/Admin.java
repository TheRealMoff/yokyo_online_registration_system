package com.beans;

public class Admin {
	
	private int id;
	private String Username;
	private String Password;
	
	//default admin constructor
	public Admin() {
		super();
		
	}

	public Admin(int id, String username, String password) {
		super();
		this.id = id;
		Username = username;
		Password = password;
	}

	public Admin(String username, String password) {
		super();
		Username = username;
		Password = password;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", Username=" + Username + ", Password=" + Password + "]";
	}

}

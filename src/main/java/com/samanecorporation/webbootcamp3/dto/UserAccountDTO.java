package com.samanecorporation.webbootcamp3.dto;


public class UserAccountDTO {

	private long id;
	private String email;
	private String password;
	private boolean state;
	private long employees_id;
	
	public UserAccountDTO() {
	
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isState() {
		return state;
	}



	public void setState(boolean state) {
		this.state = state;
	}



	public long getEmployees_id() {
		return employees_id;
	}



	public void setEmployees_id(long employees_id) {
		this.employees_id = employees_id;
	}

}

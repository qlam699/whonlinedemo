package com.donglam.webhoconline.model;

public class UserDto {

	private String passwordold;
	
	private String passwordnew;

	private String passwordconfirm;
	
	public UserDto() {
	}

	public UserDto(String passwordold, String passwordnew, String passwordconfirm) {
		super();
		this.passwordold = passwordold;
		this.passwordnew = passwordnew;
		this.passwordconfirm = passwordconfirm;
	}

	public String getPasswordold() {
		return passwordold;
	}

	public void setPasswordold(String passwordold) {
		this.passwordold = passwordold;
	}

	public String getPasswordnew() {
		return passwordnew;
	}

	public void setPasswordnew(String passwordnew) {
		this.passwordnew = passwordnew;
	}

	public String getPasswordconfirm() {
		return passwordconfirm;
	}

	public void setPasswordconfirm(String passwordconfirm) {
		this.passwordconfirm = passwordconfirm;
	}
}

package com.practice.dto;

import java.util.Date;

import jakarta.validation.constraints.*;

public class EmployeeDto 
{
	private long id;
	
	@Size(min=3 , message="AT LEAST 3 CHARACTERS REQUIRED")
	private String name;
	
	@Email(message = "PLEASE ENTER AN APPROPRIATE EMAIL")
	private String emailId;
	
	@Size(min=10 , max=10 , message="SHOULD BE 10 DIGIT")
	private String mobile;
	
	private Date date;

	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}

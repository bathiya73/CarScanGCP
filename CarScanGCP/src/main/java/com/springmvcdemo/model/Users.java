package com.springmvcdemo.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Users {
	
	@Id
	private long ID;
	@Index
	private String firstName;
	@Index
	private String lastName;
	@Index
	private Date dob;
	@Index
	private String city;
	@Index
	private int mobileNumber;
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "Users [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", city="
				+ city + ", mobileNumber=" + mobileNumber + "]";
	}
	
}


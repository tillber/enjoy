package se.madev.main.model;

import java.util.Date;

/**
 * Describes an applicant.
 * @author tillber
 *
 */
public class Applicant {
	private User user;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Date dateOfBirth;
	
	public Applicant(User user, String firstName, String lastName, String emailAddress, Date dateOfBirth) {
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.dateOfBirth = dateOfBirth;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

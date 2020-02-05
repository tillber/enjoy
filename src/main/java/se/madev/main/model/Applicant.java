package se.madev.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Describes an applicant in the recruitment application,
 * that is used for login and in applications.
 * @author madev
 *
 */
@Entity
@Table(name = "applicants")
public class Applicant {
	
	/**
	 * The primary key for the applicant.
	 */
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "user")
	private User user;
	
	/**
	 * The first name of the applicant.
	 */
	@Column(name = "fistName")
	private String firstName;
	
	/**
	 * The last name of the applicant.
	 */
	@Column(name = "lastName")
	private String lastName;
	
	/**
	 * The email-address to the applicant.
	 */
	@Column(name = "emailAddress")
	private String emailAddress;
	
	/**
	 * The birth date of the applicant.
	 */
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Returns the first name of the applicant.
	 * @return the first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the applicant.
	 * @param firstName the new first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns the last name of the applicant.
	 * @return the last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the applicant.
	 * @param lastName the new last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns the email-address to the applicant.
	 * @return the email-address.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Sets the email-address to the applicant
	 * @param emailAddress the new email-address.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Returns the date of birth of the applicant.
	 * @return the date of birth.
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * Sets the date of birth of the applicant.
	 * @param dateOfBirth the new date of birth.
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

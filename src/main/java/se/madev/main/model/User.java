package se.madev.main.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
<<<<<<< HEAD
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
=======
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
>>>>>>> 872c5535a77c8bb5fbcbf0ada1f1c960ad7ee544

@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
    @GeneratedValue //generate a unique id starting from
>>>>>>> 872c5535a77c8bb5fbcbf0ada1f1c960ad7ee544
    @Column(name="id")
    private int id;
    
    @Column(name="firstname")
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    
    @Column(name="lastname")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    
    @Column(name="email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name="username")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(name="password")
    @NotBlank(message = "Password is mandatory")
    private String password;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="dateofbirth")
    private Date dateOfBirth;
    
//    @Min(1)
//    @Max(2)
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", dateOfBirth=" + dateOfBirth + ", role="
				+ role + "]";
	}
}
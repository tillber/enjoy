package se.madev.main.model;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="application")
public class Applications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@OneToOne
	@JoinColumn(name = "applicant")
	private User applicant;



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "application", orphanRemoval = true)
	private Set<Availability> availability = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "application", orphanRemoval = true)
	private Set<Experience> experience = new HashSet<>();

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
	@OrderColumn
	private Experience[] experiences;*/

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
	@OrderColumn
	private List<Availability> availabilities;*/

	@ManyToOne
	@JoinColumn(name="status")
	private Status status;

	public Applications(){}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Set<Experience> getExperiences() {
		return this.experience;
	}

	/*public void setExperiences(Experience[] experiences) {
		this.experiences = experiences;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(Availability availabilities) {
		this.availabilities.add(availabilities);
	}*/
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
